package com.codingshuttle.youtube.hospitalManagement.service;


import com.codingshuttle.youtube.hospitalManagement.entity.RefreshToken;
import com.codingshuttle.youtube.hospitalManagement.entity.User;
import com.codingshuttle.youtube.hospitalManagement.repository.RefreshTokenRepository;
import com.codingshuttle.youtube.hospitalManagement.security.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthUtil authUtil;
    
    @Value("${jwt.refreshTokenValidityMs:604800000}")
    private long refreshTokenValidityMs;
    
    @Transactional
    public RefreshToken createRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);
        
        String token = authUtil.generateRefreshToken(user);
        
        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiryDate(Instant.now().plusMillis(refreshTokenValidityMs))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }
    
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    
    @Transactional
    public int deleteByUser(User user) {
        return refreshTokenRepository.deleteByUser(user);
    }
    
    public RefreshToken verifyExpitition(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh Token was expired.Please make a new signin request.");
        }
        return token;
    }
}
