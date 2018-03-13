package org.k3yake

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.*
import java.util.Collections.singletonMap
import java.util.stream.Stream




/**
 * Created by katsuki-miyake on 18/03/13.
 */
@RestController
class CityController{


    @GetMapping("/hello")
    fun get(): Flux<String> {
        return Flux.just("Hello", "World");
    }


    @GetMapping("/stream")
    fun stream(): Flux<Map<String, Int>> {
        val stream = Stream.iterate(0) { i -> i!! + 1 }
        return Flux.fromStream(stream.limit(10))
                .map { i -> Collections.singletonMap("value", i) }
    }
}
