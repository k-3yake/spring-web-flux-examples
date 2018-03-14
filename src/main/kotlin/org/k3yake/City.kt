package org.k3yake

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import java.util.Collections.singletonMap
import java.util.stream.Stream




/**
 * Created by katsuki-miyake on 18/03/13.
 */
@RestController
class CityController{

    @Autowired
    private lateinit var cityRepository:CityRepository

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

    @PostMapping("/city/stream")
    fun postCity(@RequestBody city:City): Mono<City> {
        return this.cityRepository.save(city)
    }


    @DeleteMapping("/city/flush")
    fun flush(){
        this.cityRepository.deleteAll().block()
    }

    @GetMapping("/city/count")
    fun count(): Count {
        return Count(this.cityRepository.count().block().toString())
    }
}



@Repository
interface CityRepository : ReactiveMongoRepository<City, String>

@Document(collection = "cities")
data class City(
        @Id val name:String
){
    constructor():this("")
}

data class Count(val count:String)
