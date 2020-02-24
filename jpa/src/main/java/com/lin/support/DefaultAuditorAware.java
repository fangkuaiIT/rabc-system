package com.lin.support;



import com.lin.constant.Constants;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * entity默认审计
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
public class DefaultAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Constants.ANONYMOUS_USER);
    }
}
