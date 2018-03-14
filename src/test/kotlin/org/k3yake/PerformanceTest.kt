package org.k3yake

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import me.mattak.moment.Moment
import org.junit.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import java.util.Collections.singletonMap
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime


/**
 * Created by katsuki-miyake on 18/03/14.
 */
class PerformanceTest {

    @Test
    fun postStream(){
        val restTemplate = RestTemplate()
        restTemplate.delete("http://localhost:8080/city/flush")
        println(" start count=" + restTemplate.getForEntity("http://localhost:8080/city/count", String::class.java).body)
        val start = System.currentTimeMillis()
        (1..10000).toList().parallelStream().forEach{
            restTemplate.exchange("http://localhost:8080/city/stream", HttpMethod.POST, HttpEntity(City(it.toString())), City::class.java)
        }
        println((System.currentTimeMillis() - start))
        println("end count=" + restTemplate.getForEntity("http://localhost:8080/city/count",String::class.java))
    }
}