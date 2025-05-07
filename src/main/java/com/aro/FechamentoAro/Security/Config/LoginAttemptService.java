package com.aro.FechamentoAro.Security.Config;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 5;
    private static final long BLOCK_DURATION_MINUTES = 15;

    @Cacheable(value = "loginAttempts", key = "#email")
    public int getAttempts(String email) {
        return 0;
    }

    @CachePut(value = "loginAttempts", key = "#email")
    public int loginFailed(String email) {
        int attempts = getAttempts(email) + 1;
        return attempts;
    }

    public void loginSucceeded(String email) {
        // Resetar tentativas após login bem-sucedido
        getAttempts(email); // Isso limpa o cache devido à implementação do CachePut
    }

    public boolean isBlocked(String email) {
        int attempts = getAttempts(email);
        return attempts >= MAX_ATTEMPTS && 
               (System.currentTimeMillis() - getLastAttemptTime(email)) < 
               TimeUnit.MINUTES.toMillis(BLOCK_DURATION_MINUTES);
    }

    @Cacheable(value = "lastAttemptTime", key = "#email")
    public long getLastAttemptTime(String email) {
        return System.currentTimeMillis();
    }
}