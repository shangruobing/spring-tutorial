package com.infoweaver.springtutorial.security;

import com.infoweaver.springtutorial.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Ruobing Shang 2023-11-03 12:16
 */
public class SecurityUtils {
    public static User getCurrentUserId() {
        LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return userDetails.getUser();
    }
}
