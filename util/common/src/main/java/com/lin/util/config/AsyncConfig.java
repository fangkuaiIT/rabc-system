package com.lin.util.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步线程池配置
 * 异常暂时使用log
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setQueueCapacity(2000);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(" method: " + method.getDeclaringClass() + " " + method.getName());
            sb.append(" params: " + params);
            sb.append(" exception: " + ex.getMessage());
            log.error("线程池发生异常 - " + sb.toString());
            sb.setLength(0);
        };
    }
}
