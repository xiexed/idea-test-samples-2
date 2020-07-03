package org.jetbrains.mvc.test.untitled

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.lang.UnsupportedOperationException


@org.springframework.boot.context.properties.ConfigurationPropertiesScan
@SpringBootApplication
class DemoApplication {


    @Bean
    open fun router1(postHandler: PostHandler) = router {
        accept(MediaType.TEXT_HTML).nest {
            GET("/") { ServerResponse.ok().render("index") }
            GET("/sse/{param1}") { ServerResponse.ok().render("sse" + it.pathVariable("param1")) }
        }
        "/api".nest {
            accept(MediaType.TEXT_EVENT_STREAM).nest {
                GET("/posts", postHandler::all)
            }

            POST("/posts", postHandler::create)
            PUT("/posts/{idu}") { postHandler.update(it) }
            DELETE("/posts/{id}", postHandler::delete)

        }
        resources("/**", ClassPathResource("static/"))
    }

}

@Component
class PostHandler {

    fun create(req: ServerRequest): Mono<ServerResponse> {

        val r = Mono.just(1).handle<Int>({ it, sink -> if (it > 0) sink.next(if (it > 1) 3 else 4) })


        return ServerResponse.ok().bodyValue(r)
    }

    fun update(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().bodyValue("" + req.pathVariable("idu"))
    }

    fun delete(req: ServerRequest): Mono<ServerResponse> = TODO()
    fun all(req: ServerRequest): Mono<ServerResponse> = TODO()

}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
