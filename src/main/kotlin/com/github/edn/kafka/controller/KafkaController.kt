package com.github.edn.kafka.controller

import com.github.edn.kafka.producer.KafkaProducer
import com.github.edn.kafka.producer.Response
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(private val kafkaProducer: KafkaProducer) {

    @PostMapping("/produce")
    fun createWithDetails(@RequestParam("topic", required = true) topic: String,
                          @RequestParam("volume", required = false, defaultValue = "1") volume: Long = 1L,
                          @RequestParam("interval_ms", required = false, defaultValue = "0") internalMillis: Long = 0L): Response {
        return kafkaProducer.createEvent(topic, volume, internalMillis)
    }
}