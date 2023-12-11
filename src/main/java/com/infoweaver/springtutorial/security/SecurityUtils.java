package com.infoweaver.springtutorial.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Ruobing Shang 2023-11-03 12:16
 */
public class SecurityUtils {
    public static Integer getCurrentUserId() {
        return Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
    }
}
