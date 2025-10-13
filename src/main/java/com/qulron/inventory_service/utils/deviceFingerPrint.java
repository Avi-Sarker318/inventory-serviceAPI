package com.qulron.inventory_service.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.security.MessageDigest;

public class deviceFingerPrint {
    public static String generateFingerprint(HttpServletRequest request) {
        String userAgent = getHeaderSafe(request, "User-Agent", "unknown-agent");
        String ip = getHeaderSafe(request, "X-Forwarded-For", request.getRemoteAddr());

        String device = detectDevice(userAgent);
        String browser = detectBrowser(userAgent);

        String raw = ip + "|" + device + "|" + browser + "|" + userAgent;

        return sha256(raw);
    }

    public static String detectDevice(String userAgent) {
        String normalized =userAgent.toLowerCase();
        if(normalized.contains("iphone")) {
            return "iphone";
        }
        else if(normalized.contains("android")) {
            return "android";
        }
        else if(normalized.contains("windows")) {
            return "windows";
        }
        else if(normalized.contains("ipad")) {
            return "ipad";
        }
        else if(normalized.contains("mac os")) {
            return "mac os";
        }
        else if(normalized.contains("linux")) {
            return "linux";
        }
        else {
            return "unknown device";
        }
    }

    private static String detectBrowser(String userAgent) {
        String normalized = userAgent.toLowerCase();

        if(normalized.contains("chrome")) {
            return "chrome";
        }
        else if(normalized.contains("opera")) {
            return "opera";
        }
        else if(normalized.contains("firefox")) {
            return "firefox";
        }
        else if(normalized.contains("edge")) {
            return "edge";
        }
        else {
            return "unknown";
        }
    }
    private static String getHeaderSafe(HttpServletRequest request, String header, String fallback) {
        String value = request.getHeader(header);
        if(value == null || value.isBlank() || "unknown".equalsIgnoreCase(value)) {
            return fallback;
        }
        return value.trim();
    }

    private static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();
            for(byte b : encoded) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            return "unknown-fingerprint";
        }
    }

}
