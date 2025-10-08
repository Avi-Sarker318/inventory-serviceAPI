package com.qulron.inventory_service.products.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

@Component
public class RateLimiter implements Filter {

    private final Bucket bucket;

    public RateLimiter() {
        Bandwidth limit = Bandwidth.classic(20,Refill.intervally(20, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        if (bucket.tryConsume(1)) {
            chain.doFilter(request,response);
        }
        else {
            HttpServletResponse httpServletResponse =(HttpServletResponse) response;
            httpServletResponse.setStatus(429);
            httpServletResponse.getWriter().write("Too Many Requests");
        }
    }
}
