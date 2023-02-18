package com.nzp.jenkins.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableAsync
@Component
public class AsyncConfig extends AsyncConfigurerSupport {
    // 核心线程数（默认线程数）
    private static final int CORE_POOL_SIZE = 100;
    // 最大线程数
    private static final int MAX_POOL_SIZE = 100;
    // 允许线程空闲时间（单位：默认为秒）
    private static final int KEEP_ALIVE_TIME = 10;
    // 缓冲队列数
    private static final int QUEUE_CAPACITY = 200;
    // 线程池名前缀
    private static final String ASYNC_SERVICE = "Async-Service-";

    // bean的名称，默认为首字母小写的方法名
    @Primary
    @Bean(value = "taskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor taskAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        executor.setThreadNamePrefix(ASYNC_SERVICE);
        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}

