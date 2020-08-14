package com.github.edn.kafka.listener

import com.github.edn.event.kafka.MyAvroEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaUtils
import org.springframework.stereotype.Component

@Component
class EventListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["my.avro.topic"], concurrency = "4", groupId = "my.app")
    fun onMessage(event: MyAvroEvent, metadata: ConsumerRecordMetadata, ack: Acknowledgment) {
        logger.info("offset=${metadata.offset()}; " +
                "partition=${metadata.partition()}; " +
                "consumerGroup=${KafkaUtils.getConsumerGroupId()}; " +
                "id=${event.id};")

        Thread.sleep(500)
        ack.acknowledge()
    }
}