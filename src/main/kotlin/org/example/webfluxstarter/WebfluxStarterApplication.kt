package org.example.webfluxstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["."])
class WebfluxStarterApplication

fun main(args: Array<String>) {
    runApplication<WebfluxStarterApplication>(*args)
}
