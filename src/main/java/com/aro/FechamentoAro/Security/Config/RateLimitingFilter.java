package com.aro.FechamentoAro.Security.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@Order(1) // Executar antes do JwtFilter
public class RateLimitingFilter extends OncePerRequestFilter {

    private static final int MAX_REQUESTS_PER_MINUTE = 100;
    private static final ConcurrentHashMap<String, RequestCounter> requestCounts = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain)
            throws ServletException, IOException {
        
        String clientIp = request.getRemoteAddr();
        String requestUri = request.getRequestURI();

        // Aplicar rate limiting apenas para endpoints de autenticação
        if (requestUri.startsWith("/auth/")) {
            RequestCounter counter = requestCounts.computeIfAbsent(clientIp, k -> new RequestCounter());
            
            if (counter.getCount() >= MAX_REQUESTS_PER_MINUTE) {
                response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), 
                                 "Limite de requisições excedido");
                return;
            }
            counter.increment();
        }

        filterChain.doFilter(request, response);
    }

    private static class RequestCounter {
        private long count;
        private long lastResetTime;

        public RequestCounter() {
            this.count = 1;
            this.lastResetTime = System.currentTimeMillis();
        }

        public synchronized void increment() {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastResetTime > TimeUnit.MINUTES.toMillis(1)) {
                count = 1;
                lastResetTime = currentTime;
            } else {
                count++;
            }
        }

        public synchronized long getCount() {
            return count;
        }
    }
}