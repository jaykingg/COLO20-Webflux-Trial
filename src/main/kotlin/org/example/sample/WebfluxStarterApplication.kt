package org.example.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxStarterApplication

fun main(args: Array<String>) {
    runApplication<WebfluxStarterApplication>(*args)
}
