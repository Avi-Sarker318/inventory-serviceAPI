package com.qulron.inventory_service.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    private final Map<String, Bucket> ipBuckets = new ConcurrentHashMap<>();
    private Bucket globalBucket = Bucket.builder()
            .addLimit(Bandwidth.classic(5,Refill.greedy(5, Duration.ofMinutes(1))))
            .build();


    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(5,Refill.greedy(5, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        String ip = getClientIp(request);
        String path = request.getRequestURI();

        if (!globalBucket.tryConsume(1)) {
            log.warn("Global rate limit reached for {} from {}", ip, path);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many Requests. Please try again later");
            return false;
        }

        if (path.contains("/products")) {
            Bucket bucket = ipBuckets.computeIfAbsent(ip, k -> createNewBucket());
            if (!bucket.tryConsume(1)) {
                log.warn("ip rate limit exceeded");
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Too many Request, try again later");
                return false;
            }
        }
        return true;
    }
    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty()){
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteUser();

    }



}
