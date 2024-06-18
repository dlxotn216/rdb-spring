package io.taesu.rdbspring.exwebmvc.config

import com.zaxxer.hikari.HikariDataSource
import io.taesu.rdbspring.exwebmvc.config.RoutingDataSource.Companion.MASTER
import io.taesu.rdbspring.exwebmvc.config.RoutingDataSource.Companion.REPLICA1
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */
@Configuration
class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.master")
    fun master(): HikariDataSource {
        return DataSourceBuilder.create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari.replica1")
    fun replica1(): HikariDataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @DependsOn("master", "replica1")
    @Bean
    fun routingDataSource(master: HikariDataSource, replica1: HikariDataSource): RoutingDataSource {
        return RoutingDataSource().apply {
            this.setTargetDataSources(
                mapOf(
                    MASTER to master,
                    REPLICA1 to replica1
                )
            )
            this.setDefaultTargetDataSource(master)
        }
    }

    @DependsOn("routingDataSource")
    @Bean
    @Primary
    fun dataSource(routingDataSource: RoutingDataSource): LazyConnectionDataSourceProxy {
        return LazyConnectionDataSourceProxy(routingDataSource)
    }
}

class RoutingDataSource: AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey() = when(TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
        true -> REPLICA1
        false -> MASTER
    }

    companion object {
        const val MASTER = "master"
        const val REPLICA1 = "replica1"
    }
}
