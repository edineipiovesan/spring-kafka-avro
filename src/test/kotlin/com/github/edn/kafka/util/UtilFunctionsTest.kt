package com.github.edn.kafka.util

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.random.Random.Default.nextLong

class UtilFunctionsTest {
    @Test
    fun `should generate a uuid properly`() {
        val uuid = generate(1).first()
        UUID.fromString(uuid)
    }

    @Test
    fun `should generate uuid list of size passed as function parameter`() {
        val volume = nextLong(100)
        val uuidList = generate(volume)

        uuidList.size shouldBe volume
    }

    @Test
    fun `should throw a illegal argument exception for uuid generate with volume zero`() {
        val exception = assertThrows<IllegalArgumentException> { generate(0) }

        exception.message shouldBe POSITIVE_GREATER_THAN_ZERO
        exception::class shouldBe IllegalArgumentException::class
    }

    @Test
    fun `should throw a illegal argument exception for uuid generate with volume less than 0`() {
        val exception = assertThrows<IllegalArgumentException> { generate(-1) }

        exception.message shouldBe POSITIVE_GREATER_THAN_ZERO
        exception::class shouldBe IllegalArgumentException::class
    }
}