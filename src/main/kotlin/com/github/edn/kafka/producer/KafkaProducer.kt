package com.github.edn.kafka.producer

import com.github.edn.event.kafka.MyAvroEvent
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import com.github.edn.kafka.util.generate

@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun createEvent(topic: String, volume: Long, internalMillis: Long): Response {
        logger.info("Starting production for $volume events...")

        val idList = generate(volume)
        idList.parallelStream().forEach { id ->
            if (internalMillis > 0) Thread.sleep(internalMillis)

            try {
                val value = MyAvroEvent()
                value.id = id
                value.message = "[$id] This is just a message content."
                val record = ProducerRecord<String, Any>(topic, id, value)
                kafkaTemplate.send(record)
            } catch (e: Exception) {
                logger.error("[$id] Kafka message delivery error; exception=${e::class.java}; message=${e.message}")
                e.printStackTrace()
            }
        }

        return Response(ids = idList.toList(), topic = topic)
    }
}
