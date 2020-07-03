package org.jetbrains.mvc.test.untitled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class JavaReactor {


    @Autowired
    PersonHandler handler;

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
//        return route()
//                .GET("/person/{id}", accept(APPLICATION_JSON), handler::getPerson)
//                .GET("/person", accept(APPLICATION_JSON), request -> {
//                    request.pathVariable("")
//                })
//                .POST("/person", handler::createPerson)
//                .build();


        Mono<Integer> r = Mono.just(1)
                .handle((i, sink) -> {
                    if (i > 0) {
                        if (i > 1) sink.next(3);
                        else sink.next(4);
                    }
                });





        return request -> r
    }

}

@Component
class PersonHandler {

    // ...

    public Mono<ServerResponse> listPeople(ServerRequest request) {
        throw new UnsupportedOperationException();
    }

    public Mono<ServerResponse> createPerson(ServerRequest request) {
        throw new UnsupportedOperationException();
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        request.pathVariable("id");
        throw new UnsupportedOperationException();
    }
}