package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.*
import com.github.ayastrebov.telegram.request.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

interface Bot {
    val token: String
    fun getLogger(): org.slf4j.Logger

    suspend fun getUpdates(params: UpdateRequest = UpdateRequest()): Response<List<Update>>
    suspend fun getMe(): Response<User>
    suspend fun getFile(fileId: String): Response<File>
    suspend fun sendMessage(params: SendMessageRequest): Response<Message>
    suspend fun sendPhoto(params: SendPhotoRequest): Response<Message>
    suspend fun sendVideo(params: SendVideoRequest): Response<Message>
    suspend fun sendVideoNote(params: SendVideoNoteRequest): Response<Message>
    suspend fun editMessageReplyMarkup(params: EditMessageReplyMarkupRequest): Response<Message>
    suspend fun answerInlineQuery(params: InlineQueryRequest): Response<Boolean>

    suspend fun setWebhook(url: String): Response<Boolean>
    suspend fun getWebhookInfo(): Response<WebhookInfo>
    suspend fun deleteWebhook(dropUpdates: Boolean = true): Response<Boolean>
}

class BotImp(override val token: String) : Bot {
    companion object {
        val logger: org.slf4j.Logger = LoggerFactory.getLogger(BotImp::class.java)
    }

    override fun getLogger(): org.slf4j.Logger = logger

    val configuration:  HttpClientConfig<*>.() -> Unit = {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = "api.telegram.org"
                path("bot${token}/")
            }
        }
    }

    val client = HttpClient(configuration)

    override suspend fun getUpdates(params: UpdateRequest): Response<List<Update>> =
        client.get("getUpdates") {
            setBody(params)
        }.body()

    override suspend fun getMe(): Response<User> = client.get("getMe").body()

    override suspend fun getFile(fileId: String): Response<File> =
        client.get("getFile") {
            parameter("file_id", fileId)
        }.body()

    override suspend fun setWebhook(url: String): Response<Boolean> =
        client.request("setWebhook") {
            parameter("url", url)
        }.body()

    override suspend fun getWebhookInfo(): Response<WebhookInfo> =
        client.request("getWebhookInfo").body()

    override suspend fun deleteWebhook(dropUpdates: Boolean): Response<Boolean> =
        client.request("deleteWebhook") {
            parameter("drop_pending_updates", dropUpdates)
        }.body()

    override suspend fun sendMessage(params: SendMessageRequest): Response<Message> =
        client.get("sendMessage") {
            setBody(params)
        }.body()

    override suspend fun sendPhoto(params: SendPhotoRequest): Response<Message> =
        client.get("sendPhoto") {
            setBody(params)
        }.body()

    override suspend fun sendVideo(params: SendVideoRequest): Response<Message> =
        client.get("sendVideo") {
            setBody(params)
        }.body()

    override suspend fun sendVideoNote(params: SendVideoNoteRequest): Response<Message> =
        client.get("sendVideoNote") {
            setBody(params)
        }.body()

    override suspend fun editMessageReplyMarkup(params: EditMessageReplyMarkupRequest): Response<Message> =
        client.get("editMessageReplyMarkup") {
            setBody(params)
        }.body()

    override suspend fun answerInlineQuery(params: InlineQueryRequest): Response<Boolean> =
        client.get("answerInlineQuery") {
            setBody(params)
        }.body()
}