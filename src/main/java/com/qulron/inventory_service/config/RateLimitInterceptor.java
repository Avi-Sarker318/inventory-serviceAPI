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

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    private final Bucket globalBucket =
            Bucket.builder()
                    .addLimit(Bandwidth.classic(10,Refill.greedy(10, Duration.ofMinutes(1))))
                    .build();

    private final Map<String, Bucket> ipBuckets = new ConcurrentHashMap<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = getClientIp(request);
        String path = request.getRequestURI();
        if (path != null && path.contains("/products")) {
            Bucket bucket = ipBuckets.computeIfAbsent(ip, k -> createIpBucket());
            if (!bucket.tryConsume(1)) {
                write(response, "ip rate limit exceeded");
                return false;
            }
        }
        if (!globalBucket.tryConsume(1)) {
            write(response, "global rate limit exceeded");
            return false;
        }
        return true;
    }

    private Bucket createIpBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1))))
                .build();
    }
    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty() && !"unknown".equalsIgnoreCase(forwarded)) {
            return forwarded.split(",")[0].trim();
        }
        String ip = request.getHeader("X-Real-IP");
        if(ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }
        return request.getRemoteAddr();

    }

    private void write(HttpServletResponse response, String message) throws Exception{
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setHeader("Retry-After", "60");
        response.getWriter().write("Too many requests! " + message);
    }


}
