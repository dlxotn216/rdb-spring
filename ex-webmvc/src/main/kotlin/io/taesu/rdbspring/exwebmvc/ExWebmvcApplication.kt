package io.taesu.rdbspring.exwebmvc

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.time.ZoneId
import java.util.*

@ConfigurationPropertiesScan
@SpringBootApplication
class ExWebmvcApplication {
	 @PostConstruct
    fun onConstruct() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")))
    }
}

fun main(args: Array<String>) {
	TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")))
	runApplication<ExWebmvcApplication>(*args)
}
