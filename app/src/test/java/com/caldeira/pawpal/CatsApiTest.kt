package com.caldeira.pawpal

import com.caldeira.pawpal.data.CatsApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class CatsApiTest {
    private val SUT = CatsApi()

    @Test
    fun `assert response is successful and with the intended limit`() {
        runBlocking {
            val limit = 10
            val response = SUT.getBreeds(limit, 0)
            assertTrue(response.isSuccessful)

            val body = response.body()
            assertNotNull(body)
            assertEquals(limit, body?.size)
        }
    }

    @Test
    fun `assert cat is parsed into dto`() {
        runBlocking {
            val limit = 10
            val response = SUT.getBreeds(limit, 0)
            assertTrue(response.isSuccessful)

            val body = response.body()
            assertNotNull(body)

            body?.firstOrNull()?.let { cat ->
                assertNotNull(cat.id)
                assertNotNull(cat.name)
                assertNotNull(cat.temperament)
                assertNotNull(cat.description)
                assertNotNull(cat.origin)
                assertNotNull(cat.lifeSpan)
            }
        }
    }
}