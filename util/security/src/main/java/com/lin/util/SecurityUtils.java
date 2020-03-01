package com.lin.util;

import com.yyfly.common.constant.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * SecurityUtils
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class SecurityUtils {

    /**
     * Get user name string.
     *
     * @return the string
     * @author : yangjunqing / 2019-01-15
     */
    public static String getUserName(){
        String userName = Constants.ANONYMOUS_USER;

        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null){
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null){
                Object principal = authentication.getPrincipal();
                if (!Constants.ANONYMOUS_USER.equalsIgnoreCase(principal.toString())){
                    UserDetails userDetails = (UserDetails) principal;
                    userName = userDetails.getUsername();
                }
            }
        }
        return userName;
    }
}
