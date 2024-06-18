package io.taesu.rdbspring.exwebmvc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
class PersistenceConfig {
    @Bean
    fun auditorAware(): AuditorAware<Long> = MockAwareFromUserContext()
}

class MockAwareFromUserContext(): AuditorAware<Long> {
    override fun getCurrentAuditor(): Optional<Long> = Optional.ofNullable(1L)
}
