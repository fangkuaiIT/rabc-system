package com.lin.util.entity;

import com.yyfly.common.constant.Constants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * entity审计
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Component
public class JpaAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

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

        return Optional.of(userName);
    }
}
