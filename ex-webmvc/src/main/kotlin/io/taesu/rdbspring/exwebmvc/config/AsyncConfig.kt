package io.taesu.rdbspring.exwebmvc.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

/**
 * Created by itaesu on 2024. 6. 24..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
@EnableAsync
@Configuration
class AsyncConfig: AsyncConfigurer {
    override fun getAsyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 5
        executor.maxPoolSize = 10
        executor.queueCapacity = 15
        executor.setThreadNamePrefix("Async-pool-")
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.AbortPolicy())
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.initialize()
        return executor
    }
}

@Component
class TestAsyncLogic {
    @Async
    fun async(i: Int, latch: CountDownLatch) {
        println("accept ${i}")
        Thread.sleep(1000L)
        latch.countDown()
    }
}
