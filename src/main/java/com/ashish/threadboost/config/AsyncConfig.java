package com.ashish.threadboost.config;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Configuration
public class AsyncConfig implements AsyncConfigurer {
    private ThreadPoolExecutor poolExecutor;

    @Override
    public synchronized Executor getAsyncExecutor(){
        if(poolExecutor == null){
            int corePoolSize = 24;
            int maxPoolSize = 60;
            int queueCapacity = 80;
            RejectedExecutionHandler rejectionPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
            poolExecutor = new ThreadPoolExecutor(
                    corePoolSize,
                    maxPoolSize,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(queueCapacity),
                    rejectionPolicy
            );
        }
        return poolExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                System.err.println("Async Error in method: " + method.getName());
                System.err.println("Exception: " + ex.getMessage());
            }
        };
    }

}
