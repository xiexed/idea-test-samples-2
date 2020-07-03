package org.jetbrains.mvc.test.untitled

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.test.StepVerifier


@SpringBootTest
class DemoApplicationTests {

    @Test
    fun contextLoads() {
    }

    private val FLUX = Flux.just("1", "2")

    fun incorrect() {
        StepVerifier.create(FLUX).expectError().verify()
    }

}
