package com.github.edn.kafka.stream

import com.github.edn.event.kafka.MyAvroEvent
import org.apache.kafka.streams.kstream.KStream
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component

@Component
@EnableBinding(KafkaStreamsProcessor::class)
class EventFilterStream {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @StreamListener("input")
    @SendTo("output")
    fun process(data: KStream<String, MyAvroEvent>): KStream<String, MyAvroEvent> {
        return data.filter {
            _, value -> hasDefaultMessage(value.message)
        }
    }

    private fun hasDefaultMessage(message: String): Boolean {
        logger.info("message=${message}")
        return message == "This is a default message"
    }
}