package com.codingshuttle.youtube.hospitalManagement.security;

import com.codingshuttle.youtube.hospitalManagement.dto.LoginRequestDto;
import com.codingshuttle.youtube.hospitalManagement.dto.LoginResponseDto;
import com.codingshuttle.youtube.hospitalManagement.dto.SignUpRequestDto;
import com.codingshuttle.youtube.hospitalManagement.dto.SignupResponseDto;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.entity.RefreshToken;
import com.codingshuttle.youtube.hospitalManagement.entity.User;
import com.codingshuttle.youtube.hospitalManagement.entity.type.AuthProviderType;
import com.codingshuttle.youtube.hospitalManagement.entity.type.RoleType;
import com.codingshuttle.youtube.hospitalManagement.repository.DoctorRepository;
import com.codingshuttle.youtube.hospitalManagement.repository.PatientRepository;
import com.codingshuttle.youtube.hospitalManagement.repository.UserRepository;
import com.codingshuttle.youtube.hospitalManagement.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        User user = principal.getUser();
        
        String accessToken = authUtil.generateAccessToken(user);
        
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        
        return new LoginResponseDto(accessToken, refreshToken.getToken(), user.getId());
    }

//    Internal signUp Method
    
    public User signUpInternal(SignUpRequestDto signupRequestDto,
                               AuthProviderType authProviderType,
                               String providerId) {
        User existing = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if (existing != null) {
            throw new IllegalArgumentException("User is already exists");
        }
        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .roles(signupRequestDto.getRoles()) // Role.PATIENT
                .providerType(authProviderType)
                .build();
        
        if (authProviderType == AuthProviderType.EMAIL) {
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }
        
        user = userRepository.save(user);
        
        Patient patient = Patient.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getUsername())
                .user(user)
                .build();
        patientRepository.save(patient);

//        Doctor doctor = Doctor.builder()
//                .name(signupRequestDto.getName())
//                .email(signupRequestDto.getUsername())
//                .user(user)
//                .build();
//        doctorRepository.save(doctor);
        
        return user;
        
    }

//    Controller signUp Method
    
    public SignupResponseDto signup(SignUpRequestDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto, AuthProviderType.EMAIL, null);
        return new SignupResponseDto(user.getId(), user.getUsername());
    }
    
    public LoginResponseDto refreshToken(String requestRefreshToken) {
        if (requestRefreshToken == null || requestRefreshToken.isBlank()) {
            throw new IllegalArgumentException("Refresh token is required..");
        }
        
        RefreshToken stored = refreshTokenService.findByToken(requestRefreshToken).orElseThrow(() -> new RuntimeException(
                "Refresh token not found.."));
        
        refreshTokenService.verifyExpitition(stored);
        
        User user = stored.getUser();
        
        String newAccessToken = authUtil.generateAccessToken(user);
        
        refreshTokenService.deleteByUser(user);
        RefreshToken newRefresh = refreshTokenService.createRefreshToken(user);
        
        return LoginResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefresh.getToken())
                .userId(user.getId())
                .build();
    }
    
    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        
        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);
        
        User user = userRepository.findByProviderIdAndProviderType(providerId,
                providerType).orElse(null); // getting login via provider(google,github.facebook.Linkedin)
        String email = oAuth2User.getAttribute("email"); // getting email via provider login(google login,github login), check from the database
        String name = oAuth2User.getAttribute("name");
        
        User emailUser = userRepository.findByUsername(email).orElse(null); // getting login via email & password
        
        if (user == null && emailUser == null) {
            // Signup flow: (Because the use have not signed his account initially)
            
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signUpInternal(new SignUpRequestDto(username, null, name, Set.of(RoleType.PATIENT)),
                    providerType,
                    providerId);
        } else if (user != null) {
            if (email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider " +
                    emailUser.getProviderType());
        }
        
        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user),
                authUtil.generateRefreshToken(user),
                user.getId());
        return ResponseEntity.ok(loginResponseDto);
        
        //  fetch ProviderId and providerType
        //  Save ProviderId and providerType
        //  if the user has an acount -direct login
        //  Othrerwise,first signup then login
    }
}
