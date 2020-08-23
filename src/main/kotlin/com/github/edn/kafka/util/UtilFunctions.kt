package com.github.edn.kafka.util

import java.util.UUID


fun generate(volume: Long): List<String> {
    if (volume < 1) throw IllegalArgumentException(POSITIVE_GREATER_THAN_ZERO)

    val idList = mutableListOf<String>()
    (1..volume).forEach {
        _ -> idList.add(UUID.randomUUID().toString().toUpperCase())
    }

    return idList
}