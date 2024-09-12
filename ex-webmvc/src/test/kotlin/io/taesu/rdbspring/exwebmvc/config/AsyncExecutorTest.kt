package io.taesu.rdbspring.exwebmvc.config

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.CountDownLatch

/**
 * Created by itaesu on 2024. 6. 24..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
@SpringBootTest
@ActiveProfiles("local")
class AsyncExecutorTest {
    @Autowired
    private lateinit var testAsyncLogic: TestAsyncLogic

    @Test
    fun `비동기 태스크 거절 테스트`() {
        val latch = CountDownLatch(31)
        (1..31).forEach {
            try {
                testAsyncLogic.async(it, latch)
            } catch (e: Exception) {
                println("${it} failed ${e.message}")
                latch.countDown()
            }
        }

        latch.await()
    }

}
