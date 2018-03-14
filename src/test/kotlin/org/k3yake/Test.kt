package org.k3yake

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.body
import reactor.core.publisher.Mono


/**
 * Created by katsuki-miyake on 18/03/14.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Tests{
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun postTest(){
        val city = City()
        city.name = "name1"

        webTestClient.post().uri("/city")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(city), City::class.java)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody()
                        .jsonPath("$.name").isEqualTo("name1")
    }

    @Test
    fun getTest(){
        val city = City()
        city.name = "name1"

        webTestClient.post().uri("/city")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(city), City::class.java)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.name").isEqualTo("name1")
    }

}