package com.github.edn.kafka.configuration

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory

@EnableKafka
@Configuration
class KafkaConfiguration(private val kafkaProperties: KafkaProperties) {

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any?> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any?>()
        factory.consumerFactory = consumerFactory()
        factory.containerProperties.ackMode = kafkaProperties.listener.ackMode
        return factory
    }

    @Bean("batch-listener")
    fun kafkaBatchListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any?> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any?>()
        factory.consumerFactory = consumerFactory()
        factory.containerProperties.ackMode = kafkaProperties.listener.ackMode
        factory.setConcurrency(4)
        factory.isBatchListener = true
        return factory
    }

    @Bean
    fun consumerConfigs(): Map<String, Any> = kafkaProperties.buildConsumerProperties()

    @Bean
    fun producerConfigs(): Map<String, Any> = kafkaProperties.buildProducerProperties()

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any?> {
        return DefaultKafkaConsumerFactory(consumerConfigs())
    }
}