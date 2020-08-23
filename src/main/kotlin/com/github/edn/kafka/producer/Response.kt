package com.github.edn.kafka.producer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Response(val ids: List<String>,
                    val topic: String,
                    val totalProduced: Int = ids.size)
