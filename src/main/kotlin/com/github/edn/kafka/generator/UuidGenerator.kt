package com.github.edn.kafka.generator

import java.util.Collections
import java.util.UUID

object UuidGenerator {

    fun generate(volume: Long): List<String> {
        val list = mutableListOf<String>()
        (1..volume).forEach { _ -> list.add(UUID.randomUUID().toString().toUpperCase()) }

        return Collections.synchronizedList(list)
    }

}