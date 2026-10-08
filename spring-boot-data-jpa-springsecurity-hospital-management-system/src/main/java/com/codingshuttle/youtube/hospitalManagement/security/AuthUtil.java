package com.codingshuttle.youtube.hospitalManagement.security;

import com.codingshuttle.youtube.hospitalManagement.entity.User;
import com.codingshuttle.youtube.hospitalManagement.entity.type.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class AuthUtil {
    
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;
    
    private final long ACCESS_TOKEN_VALIDITY_MS = 1000L * 60 * 10;
    private final long REFRESH_TOKEN_VALIDITY_MS = 1000L * 60 * 60 * 24 * 7;
    
    public SecretKey getSecretKey() {
        
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }
    
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("UserId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_MS))
                .signWith(getSecretKey())
                .compact();
    }
    
    // Simple Refresh JWT Token
    
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_MS))
                .signWith(getSecretKey())
                .compact();
    }
    
    //    public boolean validityToken(String token){
//        try{
//            Jwts.builder()
//                    .signingKey(getSecretKey())
//        }
//    }
    public String getUsernameFromToken(String token) {
        
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

//    public Date getExpirationDateFromToken(String token){
//        Claims claims = Jwts.builder()
//                .
//    }
    
    // For fetch ProviderType
    
    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId) {
        return switch (registrationId.toLowerCase()) {
            case "google" -> AuthProviderType.GOOGLE;
            case "github" -> AuthProviderType.GITHUB;
            case "facebook" -> AuthProviderType.FACEBOOK;
            case "twitter" -> AuthProviderType.TWITTER;
            case "linkedin" -> AuthProviderType.LINKEDIN;
            default -> throw new IllegalArgumentException("Unsupported OAuth2 Provider :" + registrationId);
        };
    }

//    for fetch ProviderId
    
    public String determineProviderIdFromOAuth2User(OAuth2User oAuth2User, String registrationId) {
        String providerId = switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString(); // Github gives Long datatype id
            case "facebook" -> oAuth2User.getAttribute("id").toString(); // Github gives Long datatype id
            case "linkedin" -> oAuth2User.getAttribute("sub");
            case "twitter" -> {
                Map<String, Object> data = oAuth2User.getAttribute("data");
                if (data != null && data.get("id") != null) {
                    yield data.get("id").toString();
                }
                yield null;
            }
            default -> {
                log.error("Unsupported OAuth2 provider: {}", registrationId);
                throw new IllegalArgumentException("Unsupported OAuth2 provider:" + registrationId);
            }
        };
        if (providerId == null || providerId.isBlank()) {
            log.error("Unable to determine providerId for provider: {}", registrationId);
            throw new IllegalArgumentException("Unable to determine providerId for provider: " + registrationId);
        }
        return providerId;
    }
    
    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User, String registrationId, String providerId) {
        String email = oAuth2User.getAttribute("email");
        if (email != null && !email.isBlank()) {
            return email;
        }
        return switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("login");
            case "linkedin" -> {
                // 1) Prefer email if present (already handled at top of method)
                
                // 2) Try full name from OIDC userinfo
                String name = oAuth2User.getAttribute("name");
                if (name != null && !name.isBlank()) {
                    yield name;
                }
                
                // 3) Or build from given_name + family_name
                String givenName = oAuth2User.getAttribute("given_name");
                String familyName = oAuth2User.getAttribute("family_name");
                String fullName = ((givenName != null ? givenName : "") + " " +
                        (familyName != null ? familyName : "")).trim();
                
                yield fullName.isBlank() ? providerId : fullName;
            }
            case "twitter" -> {
                Map<String, Object> data = oAuth2User.getAttribute("data");
                if (data != null && data.get("username") != null) {
                    yield data.get("username").toString();   // e.g. "b2_badal_002"
                }
                yield providerId;
            }
            case "facebook" -> {
                String name = oAuth2User.getAttribute("name");
                yield (name != null && !name.isBlank()) ? name : providerId;   // Facebook: use name
            }
            default -> providerId;
        };
    }
    
    
}
