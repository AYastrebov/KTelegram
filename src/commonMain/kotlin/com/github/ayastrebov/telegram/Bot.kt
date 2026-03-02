package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.*
import com.github.ayastrebov.telegram.request.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * Telegram Bot API client interface.
 *
 * All methods are suspend functions that return [Response] wrappers.
 * Use [getOrThrow] or [getOrNull] extensions on the response to extract results.
 */
interface Bot {
    val token: String

    // --- Updates ---

    /** Receive incoming updates using long polling. */
    suspend fun getUpdates(params: GetUpdatesRequest = GetUpdatesRequest()): Response<List<Update>>

    /** Returns basic information about the bot. */
    suspend fun getMe(): Response<User>

    // --- Messages ---

    /** Send a text message. */
    suspend fun sendMessage(params: SendMessageRequest): Response<Message>

    /** Send a photo by file_id or URL. */
    suspend fun sendPhoto(params: SendPhotoRequest): Response<Message>

    /** Send an audio file. */
    suspend fun sendAudio(params: SendAudioRequest): Response<Message>

    /** Send a general file (document). */
    suspend fun sendDocument(params: SendDocumentRequest): Response<Message>

    /** Send a video. */
    suspend fun sendVideo(params: SendVideoRequest): Response<Message>

    /** Send an animation (GIF). */
    suspend fun sendAnimation(params: SendAnimationRequest): Response<Message>

    /** Send a voice message. */
    suspend fun sendVoice(params: SendVoiceRequest): Response<Message>

    /** Send a video note (rounded square video). */
    suspend fun sendVideoNote(params: SendVideoNoteRequest): Response<Message>

    /** Send a location. */
    suspend fun sendLocation(params: SendLocationRequest): Response<Message>

    /** Send a phone contact. */
    suspend fun sendContact(params: SendContactRequest): Response<Message>

    /** Send a dice with random value. */
    suspend fun sendDice(params: SendDiceRequest): Response<Message>

    /** Send a sticker. */
    suspend fun sendSticker(params: SendStickerRequest): Response<Message>

    /** Forward a message from one chat to another. */
    suspend fun forwardMessage(params: ForwardMessageRequest): Response<Message>

    /** Copy a message (like forwarding without the "Forwarded from" header). */
    suspend fun copyMessage(params: CopyMessageRequest): Response<MessageId>

    /** Tell the user that something is happening on the bot's side (e.g., "typing…"). */
    suspend fun sendChatAction(params: SendChatActionRequest): Response<Boolean>

    // --- Editing ---

    /** Edit the text of a message. */
    suspend fun editMessageText(params: EditMessageTextRequest): Response<Message>

    /** Edit the caption of a message. */
    suspend fun editMessageCaption(params: EditMessageCaptionRequest): Response<Message>

    /** Edit the reply markup of a message. */
    suspend fun editMessageReplyMarkup(params: EditMessageReplyMarkupRequest): Response<Message>

    /** Delete a message. */
    suspend fun deleteMessage(params: DeleteMessageRequest): Response<Boolean>

    // --- Chat management ---

    /** Get up-to-date information about a chat. */
    suspend fun getChat(chatId: String): Response<Chat>

    /** Get the number of members in a chat. */
    suspend fun getChatMemberCount(chatId: String): Response<Int>

    /** Get information about a member of a chat. */
    suspend fun getChatMember(params: GetChatMemberRequest): Response<ChatMember>

    /** Ban a user in a group, supergroup, or channel. */
    suspend fun banChatMember(params: BanChatMemberRequest): Response<Boolean>

    /** Unban a previously banned user. */
    suspend fun unbanChatMember(params: UnbanChatMemberRequest): Response<Boolean>

    /** Leave a group, supergroup, or channel. */
    suspend fun leaveChat(chatId: String): Response<Boolean>

    /** Change the title of a chat. */
    suspend fun setChatTitle(params: SetChatTitleRequest): Response<Boolean>

    /** Change the description of a chat. */
    suspend fun setChatDescription(params: SetChatDescriptionRequest): Response<Boolean>

    /** Pin a message in a chat. */
    suspend fun pinChatMessage(params: PinChatMessageRequest): Response<Boolean>

    /** Unpin a message in a chat. */
    suspend fun unpinChatMessage(params: UnpinChatMessageRequest): Response<Boolean>

    // --- Callbacks ---

    /** Send an answer to a callback query from an inline keyboard. */
    suspend fun answerCallbackQuery(params: AnswerCallbackQueryRequest): Response<Boolean>

    // --- Inline ---

    /** Send answers to an inline query. */
    suspend fun answerInlineQuery(params: AnswerInlineQueryRequest): Response<Boolean>

    // --- Files ---

    /** Get basic info about a file and prepare it for downloading. */
    suspend fun getFile(fileId: String): Response<File>

    // --- Webhooks ---

    /** Set a webhook to receive updates via HTTPS POST. */
    suspend fun setWebhook(params: SetWebhookRequest): Response<Boolean>

    /** Get current webhook status. */
    suspend fun getWebhookInfo(): Response<WebhookInfo>

    /** Remove webhook and switch to getUpdates. */
    suspend fun deleteWebhook(dropUpdates: Boolean = true): Response<Boolean>

    // --- Commands ---

    /** Set the list of the bot's commands. */
    suspend fun setMyCommands(params: SetMyCommandsRequest): Response<Boolean>

    /** Get the current list of the bot's commands. */
    suspend fun getMyCommands(): Response<List<BotCommand>>

    /** Delete the list of the bot's commands. */
    suspend fun deleteMyCommands(): Response<Boolean>

    // --- Lifecycle ---

    /** Close the underlying HTTP client and release resources. */
    fun close()
}

/**
 * Default [Bot] implementation backed by Ktor [HttpClient].
 *
 * @param token Bot API token obtained from BotFather.
 * @param engine Optional HTTP client engine for testing (e.g., MockEngine).
 * @param configure Optional additional HttpClient configuration block.
 */
class BotImp(
    override val token: String,
    engine: HttpClientEngine? = null,
    configure: HttpClientConfig<*>.() -> Unit = {},
) : Bot {

    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val client: HttpClient = (engine?.let { HttpClient(it) } ?: HttpClient()).config {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }

        install(ContentNegotiation) {
            json(this@BotImp.json)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = "api.telegram.org"
                path("bot${token}/")
            }
        }

        configure()
    }

    // --- Updates ---

    override suspend fun getUpdates(params: GetUpdatesRequest): Response<List<Update>> =
        client.post("getUpdates") { setBody(params) }.body()

    override suspend fun getMe(): Response<User> =
        client.post("getMe").body()

    // --- Messages ---

    override suspend fun sendMessage(params: SendMessageRequest): Response<Message> =
        client.post("sendMessage") { setBody(params) }.body()

    override suspend fun sendPhoto(params: SendPhotoRequest): Response<Message> =
        client.post("sendPhoto") { setBody(params) }.body()

    override suspend fun sendAudio(params: SendAudioRequest): Response<Message> =
        client.post("sendAudio") { setBody(params) }.body()

    override suspend fun sendDocument(params: SendDocumentRequest): Response<Message> =
        client.post("sendDocument") { setBody(params) }.body()

    override suspend fun sendVideo(params: SendVideoRequest): Response<Message> =
        client.post("sendVideo") { setBody(params) }.body()

    override suspend fun sendAnimation(params: SendAnimationRequest): Response<Message> =
        client.post("sendAnimation") { setBody(params) }.body()

    override suspend fun sendVoice(params: SendVoiceRequest): Response<Message> =
        client.post("sendVoice") { setBody(params) }.body()

    override suspend fun sendVideoNote(params: SendVideoNoteRequest): Response<Message> =
        client.post("sendVideoNote") { setBody(params) }.body()

    override suspend fun sendLocation(params: SendLocationRequest): Response<Message> =
        client.post("sendLocation") { setBody(params) }.body()

    override suspend fun sendContact(params: SendContactRequest): Response<Message> =
        client.post("sendContact") { setBody(params) }.body()

    override suspend fun sendDice(params: SendDiceRequest): Response<Message> =
        client.post("sendDice") { setBody(params) }.body()

    override suspend fun sendSticker(params: SendStickerRequest): Response<Message> =
        client.post("sendSticker") { setBody(params) }.body()

    override suspend fun forwardMessage(params: ForwardMessageRequest): Response<Message> =
        client.post("forwardMessage") { setBody(params) }.body()

    override suspend fun copyMessage(params: CopyMessageRequest): Response<MessageId> =
        client.post("copyMessage") { setBody(params) }.body()

    override suspend fun sendChatAction(params: SendChatActionRequest): Response<Boolean> =
        client.post("sendChatAction") { setBody(params) }.body()

    // --- Editing ---

    override suspend fun editMessageText(params: EditMessageTextRequest): Response<Message> =
        client.post("editMessageText") { setBody(params) }.body()

    override suspend fun editMessageCaption(params: EditMessageCaptionRequest): Response<Message> =
        client.post("editMessageCaption") { setBody(params) }.body()

    override suspend fun editMessageReplyMarkup(params: EditMessageReplyMarkupRequest): Response<Message> =
        client.post("editMessageReplyMarkup") { setBody(params) }.body()

    override suspend fun deleteMessage(params: DeleteMessageRequest): Response<Boolean> =
        client.post("deleteMessage") { setBody(params) }.body()

    // --- Chat management ---

    override suspend fun getChat(chatId: String): Response<Chat> =
        client.post("getChat") { setBody(mapOf("chat_id" to chatId)) }.body()

    override suspend fun getChatMemberCount(chatId: String): Response<Int> =
        client.post("getChatMemberCount") { setBody(mapOf("chat_id" to chatId)) }.body()

    override suspend fun getChatMember(params: GetChatMemberRequest): Response<ChatMember> =
        client.post("getChatMember") { setBody(params) }.body()

    override suspend fun banChatMember(params: BanChatMemberRequest): Response<Boolean> =
        client.post("banChatMember") { setBody(params) }.body()

    override suspend fun unbanChatMember(params: UnbanChatMemberRequest): Response<Boolean> =
        client.post("unbanChatMember") { setBody(params) }.body()

    override suspend fun leaveChat(chatId: String): Response<Boolean> =
        client.post("leaveChat") { setBody(mapOf("chat_id" to chatId)) }.body()

    override suspend fun setChatTitle(params: SetChatTitleRequest): Response<Boolean> =
        client.post("setChatTitle") { setBody(params) }.body()

    override suspend fun setChatDescription(params: SetChatDescriptionRequest): Response<Boolean> =
        client.post("setChatDescription") { setBody(params) }.body()

    override suspend fun pinChatMessage(params: PinChatMessageRequest): Response<Boolean> =
        client.post("pinChatMessage") { setBody(params) }.body()

    override suspend fun unpinChatMessage(params: UnpinChatMessageRequest): Response<Boolean> =
        client.post("unpinChatMessage") { setBody(params) }.body()

    // --- Callbacks ---

    override suspend fun answerCallbackQuery(params: AnswerCallbackQueryRequest): Response<Boolean> =
        client.post("answerCallbackQuery") { setBody(params) }.body()

    // --- Inline ---

    override suspend fun answerInlineQuery(params: AnswerInlineQueryRequest): Response<Boolean> =
        client.post("answerInlineQuery") { setBody(params) }.body()

    // --- Files ---

    override suspend fun getFile(fileId: String): Response<File> =
        client.post("getFile") { setBody(mapOf("file_id" to fileId)) }.body()

    // --- Webhooks ---

    override suspend fun setWebhook(params: SetWebhookRequest): Response<Boolean> =
        client.post("setWebhook") { setBody(params) }.body()

    override suspend fun getWebhookInfo(): Response<WebhookInfo> =
        client.post("getWebhookInfo").body()

    override suspend fun deleteWebhook(dropUpdates: Boolean): Response<Boolean> =
        client.post("deleteWebhook") { setBody(mapOf("drop_pending_updates" to dropUpdates)) }.body()

    // --- Commands ---

    override suspend fun setMyCommands(params: SetMyCommandsRequest): Response<Boolean> =
        client.post("setMyCommands") { setBody(params) }.body()

    override suspend fun getMyCommands(): Response<List<BotCommand>> =
        client.post("getMyCommands").body()

    override suspend fun deleteMyCommands(): Response<Boolean> =
        client.post("deleteMyCommands").body()

    // --- Lifecycle ---

    override fun close() {
        client.close()
    }
}

/**
 * Creates a new [Bot] instance.
 *
 * ```kotlin
 * val bot = TelegramBot("123456:ABC-DEF")
 * val me = bot.getMe().getOrThrow()
 * ```
 *
 * @param token Bot API token from BotFather.
 * @param engine Optional HTTP client engine (useful for testing with MockEngine).
 * @param configure Optional additional HttpClient configuration.
 */
fun TelegramBot(
    token: String,
    engine: HttpClientEngine? = null,
    configure: HttpClientConfig<*>.() -> Unit = {},
): Bot = BotImp(token, engine, configure)
