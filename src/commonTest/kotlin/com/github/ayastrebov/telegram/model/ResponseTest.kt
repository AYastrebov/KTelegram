package com.github.ayastrebov.telegram.model

import kotlin.test.*

class ResponseTest {

    @Test
    fun getOrThrowReturnsResultOnSuccess() {
        val response = Response(ok = true, result = "hello")
        assertEquals("hello", response.getOrThrow())
    }

    @Test
    fun getOrThrowThrowsOnError() {
        val response = Response<String>(ok = false, errorCode = 404, description = "Not Found")
        val ex = assertFailsWith<TelegramApiException> { response.getOrThrow() }
        assertEquals(404, ex.errorCode)
        assertTrue(ex.message!!.contains("Not Found"))
    }

    @Test
    fun getOrThrowThrowsWhenResultIsNull() {
        val response = Response<String>(ok = true, result = null)
        assertFailsWith<TelegramApiException> { response.getOrThrow() }
    }

    @Test
    fun getOrNullReturnsResultOnSuccess() {
        val response = Response(ok = true, result = 42)
        assertEquals(42, response.getOrNull())
    }

    @Test
    fun getOrNullReturnsNullOnError() {
        val response = Response<Int>(ok = false, errorCode = 400, description = "Bad Request")
        assertNull(response.getOrNull())
    }

    @Test
    fun isErrorTrueForFailedResponse() {
        val response = Response<String>(ok = false, errorCode = 500, description = "Internal Server Error")
        assertTrue(response.isError)
    }

    @Test
    fun isErrorFalseForSuccessfulResponse() {
        val response = Response(ok = true, result = "ok")
        assertFalse(response.isError)
    }
}
