package org.example.sample

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.web.reactive.config.EnableWebFlux
import java.util.*


@EnableWebFlux
@SpringBootApplication
class WebfluxStarterApplication {
    @Bean
    fun initializer(connectionFactory: ConnectionFactory?): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory!!)
        initializer.setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema/Guide-Create.sql")))
        return initializer
    }


}

fun main(args: Array<String>) {
    Locale.setDefault(Locale.KOREA)
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    runApplication<WebfluxStarterApplication>(*args)
}




