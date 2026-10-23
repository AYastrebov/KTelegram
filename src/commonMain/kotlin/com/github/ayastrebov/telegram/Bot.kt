package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.*
import com.github.ayastrebov.telegram.request.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
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
public interface Bot {
    // --- Updates ---

    /** Receive incoming updates using long polling. */
    public suspend fun getUpdates(params: GetUpdatesRequest = GetUpdatesRequest()): Response<List<Update>>

    /** Returns basic information about the bot. */
    public suspend fun getMe(): Response<User>

    // --- Messages ---

    /** Send a text message. */
    public suspend fun sendMessage(params: SendMessageRequest): Response<Message>

    /** Send a photo by file_id or URL. */
    public suspend fun sendPhoto(params: SendPhotoRequest): Response<Message>

    /** Send an audio file. */
    public suspend fun sendAudio(params: SendAudioRequest): Response<Message>

    /** Send a general file (document). */
    public suspend fun sendDocument(params: SendDocumentRequest): Response<Message>

    /** Send a video. */
    public suspend fun sendVideo(params: SendVideoRequest): Response<Message>

    /** Send an animation (GIF). */
    public suspend fun sendAnimation(params: SendAnimationRequest): Response<Message>

    /** Send a voice message. */
    public suspend fun sendVoice(params: SendVoiceRequest): Response<Message>

    /** Send a video note (rounded square video). */
    public suspend fun sendVideoNote(params: SendVideoNoteRequest): Response<Message>

    /** Send a location. */
    public suspend fun sendLocation(params: SendLocationRequest): Response<Message>

    /** Send a phone contact. */
    public suspend fun sendContact(params: SendContactRequest): Response<Message>

    /** Send a dice with random value. */
    public suspend fun sendDice(params: SendDiceRequest): Response<Message>

    /** Send a sticker. */
    public suspend fun sendSticker(params: SendStickerRequest): Response<Message>

    /** Send a native poll. */
    public suspend fun sendPoll(params: SendPollRequest): Response<Message>

    /** Forward a message from one chat to another. */
    public suspend fun forwardMessage(params: ForwardMessageRequest): Response<Message>

    /** Forward multiple messages from one chat to another. */
    public suspend fun forwardMessages(params: ForwardMessagesRequest): Response<List<MessageId>>

    /** Copy a message (like forwarding without the "Forwarded from" header). */
    public suspend fun copyMessage(params: CopyMessageRequest): Response<MessageId>

    /** Copy multiple messages from one chat to another. */
    public suspend fun copyMessages(params: CopyMessagesRequest): Response<List<MessageId>>

    /** Send a group of photos, videos, documents or audios as an album. */
    public suspend fun sendMediaGroup(params: SendMediaGroupRequest): Response<List<Message>>

    /** Delete multiple messages in one request. */
    public suspend fun deleteMessages(params: DeleteMessagesRequest): Response<Boolean>

    /** Tell the user that something is happening on the bot's side (e.g., "typing…"). */
    public suspend fun sendChatAction(params: SendChatActionRequest): Response<Boolean>

    /** Send a message draft for streaming partial messages to a user (private chats only). */
    public suspend fun sendMessageDraft(params: SendMessageDraftRequest): Response<Boolean>

    // --- Editing ---

    /** Edit the text of a message. */
    public suspend fun editMessageText(params: EditMessageTextRequest): Response<EditMessageResult>

    /** Edit the caption of a message. */
    public suspend fun editMessageCaption(params: EditMessageCaptionRequest): Response<EditMessageResult>

    /** Edit the reply markup of a message. */
    public suspend fun editMessageReplyMarkup(params: EditMessageReplyMarkupRequest): Response<EditMessageResult>

    /** Delete a message. */
    public suspend fun deleteMessage(params: DeleteMessageRequest): Response<Boolean>

    // --- Chat management ---

    /** Get up-to-date information about a chat. */
    public suspend fun getChat(params: GetChatRequest): Response<Chat>

    /** Get the number of members in a chat. */
    public suspend fun getChatMemberCount(params: GetChatMemberCountRequest): Response<Int>

    /** Get information about a member of a chat. */
    public suspend fun getChatMember(params: GetChatMemberRequest): Response<ChatMember>

    /** Get the list of administrators in a chat. */
    public suspend fun getChatAdministrators(params: GetChatAdministratorsRequest): Response<List<ChatMember>>

    /** Ban a user in a group, supergroup, or channel. */
    public suspend fun banChatMember(params: BanChatMemberRequest): Response<Boolean>

    /** Unban a previously banned user. */
    public suspend fun unbanChatMember(params: UnbanChatMemberRequest): Response<Boolean>

    /** Restrict a user in a supergroup. */
    public suspend fun restrictChatMember(params: RestrictChatMemberRequest): Response<Boolean>

    /** Leave a group, supergroup, or channel. */
    public suspend fun leaveChat(params: LeaveChatRequest): Response<Boolean>

    /** Change the title of a chat. */
    public suspend fun setChatTitle(params: SetChatTitleRequest): Response<Boolean>

    /** Change the description of a chat. */
    public suspend fun setChatDescription(params: SetChatDescriptionRequest): Response<Boolean>

    /** Pin a message in a chat. */
    public suspend fun pinChatMessage(params: PinChatMessageRequest): Response<Boolean>

    /** Unpin a message in a chat. */
    public suspend fun unpinChatMessage(params: UnpinChatMessageRequest): Response<Boolean>

    /** Promote or demote a user in a supergroup or channel. */
    public suspend fun promoteChatMember(params: PromoteChatMemberRequest): Response<Boolean>

    /** Set a tag for a chat member. */
    public suspend fun setChatMemberTag(params: SetChatMemberTagRequest): Response<Boolean>

    /** Generate a new primary invite link for a chat. */
    public suspend fun exportChatInviteLink(params: ExportChatInviteLinkRequest): Response<String>

    /** Create an additional invite link for a chat. */
    public suspend fun createChatInviteLink(params: CreateChatInviteLinkRequest): Response<ChatInviteLink>

    /** Approve a chat join request. */
    public suspend fun approveChatJoinRequest(params: ApproveChatJoinRequestParams): Response<Boolean>

    /** Decline a chat join request. */
    public suspend fun declineChatJoinRequest(params: DeclineChatJoinRequestParams): Response<Boolean>

    // --- Forum Topics ---

    /** Create a topic in a forum supergroup chat. */
    public suspend fun createForumTopic(params: CreateForumTopicRequest): Response<ForumTopic>

    // --- Callbacks ---

    /** Send an answer to a callback query from an inline keyboard. */
    public suspend fun answerCallbackQuery(params: AnswerCallbackQueryRequest): Response<Boolean>

    // --- Inline ---

    /** Send answers to an inline query. */
    public suspend fun answerInlineQuery(params: AnswerInlineQueryRequest): Response<Boolean>

    // --- Files ---

    /** Get basic info about a file and prepare it for downloading. */
    public suspend fun getFile(params: GetFileRequest): Response<File>

    // --- Webhooks ---

    /** Set a webhook to receive updates via HTTPS POST. */
    public suspend fun setWebhook(params: SetWebhookRequest): Response<Boolean>

    /** Get current webhook status. */
    public suspend fun getWebhookInfo(): Response<WebhookInfo>

    /** Remove webhook and switch to getUpdates. */
    public suspend fun deleteWebhook(params: DeleteWebhookRequest = DeleteWebhookRequest()): Response<Boolean>

    // --- Commands ---

    /** Set the list of the bot's commands. */
    public suspend fun setMyCommands(params: SetMyCommandsRequest): Response<Boolean>

    /** Get the current list of the bot's commands for the given scope and language. */
    public suspend fun getMyCommands(params: GetMyCommandsRequest = GetMyCommandsRequest()): Response<List<BotCommand>>

    /** Delete the list of the bot's commands for the given scope and language. */
    public suspend fun deleteMyCommands(params: DeleteMyCommandsRequest = DeleteMyCommandsRequest()): Response<Boolean>

    // --- Managed Bots ---

    /** Get the token of a managed bot. */
    public suspend fun getManagedBotToken(params: GetManagedBotTokenRequest): Response<String>

    /** Revoke the current token of a managed bot and generate a new one. */
    public suspend fun replaceManagedBotToken(params: ReplaceManagedBotTokenRequest): Response<String>

    /** Save a prepared keyboard button for use in a Mini App. */
    public suspend fun savePreparedKeyboardButton(params: SavePreparedKeyboardButtonRequest): Response<PreparedKeyboardButton>

    // --- Profile ---

    /** Change the bot's profile photo. */
    public suspend fun setMyProfilePhoto(params: SetMyProfilePhotoRequest): Response<Boolean>

    /** Remove the bot's profile photo. */
    public suspend fun removeMyProfilePhoto(): Response<Boolean>

    /** Log out from the cloud Bot API server before running the bot locally. */
    public suspend fun logOut(): Response<Boolean>

    /**
     * Close the bot instance on Telegram Bot API side.
     *
     * Named `closeBot` to avoid conflict with [close] that closes local HTTP resources.
     */
    public suspend fun closeBot(): Response<Boolean>

    // --- Messages (additional) ---

    /** Send information about a venue. */
    public suspend fun sendVenue(params: SendVenueRequest): Response<Message>

    // --- Editing (additional) ---

    /** Edit animation, audio, document, photo, or video messages. */
    public suspend fun editMessageMedia(params: EditMessageMediaRequest): Response<EditMessageResult>

    /** Edit a live location message. */
    public suspend fun editMessageLiveLocation(params: EditMessageLiveLocationRequest): Response<EditMessageResult>

    /** Stop updating a live location message before live_period expires. */
    public suspend fun stopMessageLiveLocation(params: StopMessageLiveLocationRequest): Response<EditMessageResult>

    /** Change the chosen reactions on a message. */
    public suspend fun setMessageReaction(params: SetMessageReactionRequest): Response<Boolean>

    /** Stop a poll which was sent by the bot. */
    public suspend fun stopPoll(params: StopPollRequest): Response<Poll>

    // --- Chat management (additional) ---

    /** Set default chat permissions for all members. */
    public suspend fun setChatPermissions(params: SetChatPermissionsRequest): Response<Boolean>

    /** Set a custom title for an administrator promoted by the bot. */
    public suspend fun setChatAdministratorCustomTitle(params: SetChatAdministratorCustomTitleRequest): Response<Boolean>

    /** Ban a channel chat in a supergroup or a channel. */
    public suspend fun banChatSenderChat(params: BanChatSenderChatRequest): Response<Boolean>

    /** Unban a previously banned channel chat. */
    public suspend fun unbanChatSenderChat(params: UnbanChatSenderChatRequest): Response<Boolean>

    /** Delete a chat photo. */
    public suspend fun deleteChatPhoto(params: DeleteChatPhotoRequest): Response<Boolean>

    /** Clear the list of pinned messages in a chat. */
    public suspend fun unpinAllChatMessages(params: UnpinAllChatMessagesRequest): Response<Boolean>

    /** Edit a non-primary invite link created by the bot. */
    public suspend fun editChatInviteLink(params: EditChatInviteLinkRequest): Response<ChatInviteLink>

    /** Revoke an invite link created by the bot. */
    public suspend fun revokeChatInviteLink(params: RevokeChatInviteLinkRequest): Response<ChatInviteLink>

    /** Get a list of profile pictures for a user. */
    public suspend fun getUserProfilePhotos(params: GetUserProfilePhotosRequest): Response<UserProfilePhotos>

    /** Get the list of boosts added to a chat by a user. */
    public suspend fun getUserChatBoosts(params: GetUserChatBoostsRequest): Response<UserChatBoosts>

    /** Get information about the connection of the bot with a business account. */
    public suspend fun getBusinessConnection(params: GetBusinessConnectionRequest): Response<BusinessConnection>

    // --- Forum Topics (additional) ---

    /** Edit name and icon of a topic in a forum supergroup chat. */
    public suspend fun editForumTopic(params: EditForumTopicRequest): Response<Boolean>

    /** Close an open topic in a forum supergroup chat. */
    public suspend fun closeForumTopic(params: CloseForumTopicRequest): Response<Boolean>

    /** Reopen a closed topic in a forum supergroup chat. */
    public suspend fun reopenForumTopic(params: ReopenForumTopicRequest): Response<Boolean>

    /** Delete a forum topic along with all its messages. */
    public suspend fun deleteForumTopic(params: DeleteForumTopicRequest): Response<Boolean>

    /** Clear the list of pinned messages in a forum topic. */
    public suspend fun unpinAllForumTopicMessages(params: UnpinAllForumTopicMessagesRequest): Response<Boolean>

    // --- Commands (additional) ---

    /** Change the bot's name. */
    public suspend fun setMyName(params: SetMyNameRequest): Response<Boolean>

    /** Get the current bot name for the given user language. */
    public suspend fun getMyName(params: GetMyNameRequest = GetMyNameRequest()): Response<BotName>

    /** Change the bot's description shown in the chat with the bot if the chat is empty. */
    public suspend fun setMyDescription(params: SetMyDescriptionRequest): Response<Boolean>

    /** Get the current bot description for the given user language. */
    public suspend fun getMyDescription(params: GetMyDescriptionRequest = GetMyDescriptionRequest()): Response<BotDescription>

    /** Change the bot's short description shown on the bot's profile page. */
    public suspend fun setMyShortDescription(params: SetMyShortDescriptionRequest): Response<Boolean>

    /** Get the current bot short description for the given user language. */
    public suspend fun getMyShortDescription(params: GetMyShortDescriptionRequest = GetMyShortDescriptionRequest()): Response<BotShortDescription>

    /** Change the default administrator rights requested by the bot when it's added as an administrator. */
    public suspend fun setMyDefaultAdministratorRights(params: SetMyDefaultAdministratorRightsRequest): Response<Boolean>

    /** Get the current default administrator rights of the bot. */
    public suspend fun getMyDefaultAdministratorRights(params: GetMyDefaultAdministratorRightsRequest = GetMyDefaultAdministratorRightsRequest()): Response<ChatAdministratorRights>

    /** Change the bot's menu button in a private chat, or the default menu button. */
    public suspend fun setChatMenuButton(params: SetChatMenuButtonRequest): Response<Boolean>

    /** Get the current value of the bot's menu button in a private chat, or the default menu button. */
    public suspend fun getChatMenuButton(params: GetChatMenuButtonRequest = GetChatMenuButtonRequest()): Response<MenuButton>

    // --- Payments (additional) ---

    /** Respond to a pre-checkout query (must be answered within 10 seconds). */
    public suspend fun answerPreCheckoutQuery(params: AnswerPreCheckoutQueryRequest): Response<Boolean>

    /** Reply to a shipping query for invoices with flexible price. */
    public suspend fun answerShippingQuery(params: AnswerShippingQueryRequest): Response<Boolean>

    // --- Lifecycle ---

    /** Close the underlying HTTP client and release resources. */
    public fun close()
}

/**
 * Default [Bot] implementation backed by Ktor [HttpClient].
 *
 * @param token Bot API token obtained from BotFather.
 * @param engine Optional HTTP client engine for testing (e.g., MockEngine).
 * @param configure Optional additional HttpClient configuration block.
 */
internal class BotImp(
    private val token: String,
    engine: HttpClientEngine? = null,
    configure: HttpClientConfig<*>.() -> Unit = {},
) : Bot {

    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val client: HttpClient = (engine?.let { HttpClient(it) } ?: HttpClient()).config {
        install(ContentNegotiation) {
            json(this@BotImp.json)
        }

        install(HttpTimeout)

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
        client.post("getUpdates") {
            setBody(params)
            // Long polling: give the request the Telegram timeout plus a grace period, otherwise
            // engine defaults (e.g. 15 s on CIO) abort the poll before Telegram answers.
            params.timeout?.let { timeout { requestTimeoutMillis = (it + LONG_POLL_GRACE_SECONDS) * 1000 } }
        }.body()

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

    override suspend fun sendPoll(params: SendPollRequest): Response<Message> =
        client.post("sendPoll") { setBody(params) }.body()

    override suspend fun forwardMessage(params: ForwardMessageRequest): Response<Message> =
        client.post("forwardMessage") { setBody(params) }.body()

    override suspend fun forwardMessages(params: ForwardMessagesRequest): Response<List<MessageId>> =
        client.post("forwardMessages") { setBody(params) }.body()

    override suspend fun copyMessage(params: CopyMessageRequest): Response<MessageId> =
        client.post("copyMessage") { setBody(params) }.body()

    override suspend fun copyMessages(params: CopyMessagesRequest): Response<List<MessageId>> =
        client.post("copyMessages") { setBody(params) }.body()

    override suspend fun sendMediaGroup(params: SendMediaGroupRequest): Response<List<Message>> =
        client.post("sendMediaGroup") { setBody(params) }.body()

    override suspend fun deleteMessages(params: DeleteMessagesRequest): Response<Boolean> =
        client.post("deleteMessages") { setBody(params) }.body()

    override suspend fun sendChatAction(params: SendChatActionRequest): Response<Boolean> =
        client.post("sendChatAction") { setBody(params) }.body()

    override suspend fun sendMessageDraft(params: SendMessageDraftRequest): Response<Boolean> =
        client.post("sendMessageDraft") { setBody(params) }.body()

    // --- Editing ---

    override suspend fun editMessageText(params: EditMessageTextRequest): Response<EditMessageResult> =
        client.post("editMessageText") { setBody(params) }.body()

    override suspend fun editMessageCaption(params: EditMessageCaptionRequest): Response<EditMessageResult> =
        client.post("editMessageCaption") { setBody(params) }.body()

    override suspend fun editMessageReplyMarkup(params: EditMessageReplyMarkupRequest): Response<EditMessageResult> =
        client.post("editMessageReplyMarkup") { setBody(params) }.body()

    override suspend fun deleteMessage(params: DeleteMessageRequest): Response<Boolean> =
        client.post("deleteMessage") { setBody(params) }.body()

    // --- Chat management ---

    override suspend fun getChat(params: GetChatRequest): Response<Chat> =
        client.post("getChat") { setBody(params) }.body()

    override suspend fun getChatMemberCount(params: GetChatMemberCountRequest): Response<Int> =
        client.post("getChatMemberCount") { setBody(params) }.body()

    override suspend fun getChatMember(params: GetChatMemberRequest): Response<ChatMember> =
        client.post("getChatMember") { setBody(params) }.body()

    override suspend fun getChatAdministrators(params: GetChatAdministratorsRequest): Response<List<ChatMember>> =
        client.post("getChatAdministrators") { setBody(params) }.body()

    override suspend fun banChatMember(params: BanChatMemberRequest): Response<Boolean> =
        client.post("banChatMember") { setBody(params) }.body()

    override suspend fun unbanChatMember(params: UnbanChatMemberRequest): Response<Boolean> =
        client.post("unbanChatMember") { setBody(params) }.body()

    override suspend fun restrictChatMember(params: RestrictChatMemberRequest): Response<Boolean> =
        client.post("restrictChatMember") { setBody(params) }.body()

    override suspend fun leaveChat(params: LeaveChatRequest): Response<Boolean> =
        client.post("leaveChat") { setBody(params) }.body()

    override suspend fun setChatTitle(params: SetChatTitleRequest): Response<Boolean> =
        client.post("setChatTitle") { setBody(params) }.body()

    override suspend fun setChatDescription(params: SetChatDescriptionRequest): Response<Boolean> =
        client.post("setChatDescription") { setBody(params) }.body()

    override suspend fun pinChatMessage(params: PinChatMessageRequest): Response<Boolean> =
        client.post("pinChatMessage") { setBody(params) }.body()

    override suspend fun unpinChatMessage(params: UnpinChatMessageRequest): Response<Boolean> =
        client.post("unpinChatMessage") { setBody(params) }.body()

    override suspend fun promoteChatMember(params: PromoteChatMemberRequest): Response<Boolean> =
        client.post("promoteChatMember") { setBody(params) }.body()

    override suspend fun setChatMemberTag(params: SetChatMemberTagRequest): Response<Boolean> =
        client.post("setChatMemberTag") { setBody(params) }.body()

    override suspend fun exportChatInviteLink(params: ExportChatInviteLinkRequest): Response<String> =
        client.post("exportChatInviteLink") { setBody(params) }.body()

    override suspend fun createChatInviteLink(params: CreateChatInviteLinkRequest): Response<ChatInviteLink> =
        client.post("createChatInviteLink") { setBody(params) }.body()

    override suspend fun approveChatJoinRequest(params: ApproveChatJoinRequestParams): Response<Boolean> =
        client.post("approveChatJoinRequest") { setBody(params) }.body()

    override suspend fun declineChatJoinRequest(params: DeclineChatJoinRequestParams): Response<Boolean> =
        client.post("declineChatJoinRequest") { setBody(params) }.body()

    // --- Forum Topics ---

    override suspend fun createForumTopic(params: CreateForumTopicRequest): Response<ForumTopic> =
        client.post("createForumTopic") { setBody(params) }.body()

    // --- Callbacks ---

    override suspend fun answerCallbackQuery(params: AnswerCallbackQueryRequest): Response<Boolean> =
        client.post("answerCallbackQuery") { setBody(params) }.body()

    // --- Inline ---

    override suspend fun answerInlineQuery(params: AnswerInlineQueryRequest): Response<Boolean> =
        client.post("answerInlineQuery") { setBody(params) }.body()

    // --- Files ---

    override suspend fun getFile(params: GetFileRequest): Response<File> =
        client.post("getFile") { setBody(params) }.body()

    // --- Webhooks ---

    override suspend fun setWebhook(params: SetWebhookRequest): Response<Boolean> =
        client.post("setWebhook") { setBody(params) }.body()

    override suspend fun getWebhookInfo(): Response<WebhookInfo> =
        client.post("getWebhookInfo").body()

    override suspend fun deleteWebhook(params: DeleteWebhookRequest): Response<Boolean> =
        client.post("deleteWebhook") { setBody(params) }.body()

    // --- Commands ---

    override suspend fun setMyCommands(params: SetMyCommandsRequest): Response<Boolean> =
        client.post("setMyCommands") { setBody(params) }.body()

    override suspend fun getMyCommands(params: GetMyCommandsRequest): Response<List<BotCommand>> =
        client.post("getMyCommands") { setBody(params) }.body()

    override suspend fun deleteMyCommands(params: DeleteMyCommandsRequest): Response<Boolean> =
        client.post("deleteMyCommands") { setBody(params) }.body()

    // --- Managed Bots ---

    override suspend fun getManagedBotToken(params: GetManagedBotTokenRequest): Response<String> =
        client.post("getManagedBotToken") { setBody(params) }.body()

    override suspend fun replaceManagedBotToken(params: ReplaceManagedBotTokenRequest): Response<String> =
        client.post("replaceManagedBotToken") { setBody(params) }.body()

    override suspend fun savePreparedKeyboardButton(params: SavePreparedKeyboardButtonRequest): Response<PreparedKeyboardButton> =
        client.post("savePreparedKeyboardButton") { setBody(params) }.body()

    // --- Profile ---

    override suspend fun setMyProfilePhoto(params: SetMyProfilePhotoRequest): Response<Boolean> =
        client.post("setMyProfilePhoto") { setBody(params) }.body()

    override suspend fun removeMyProfilePhoto(): Response<Boolean> =
        client.post("removeMyProfilePhoto").body()

    override suspend fun logOut(): Response<Boolean> =
        client.post("logOut").body()

    override suspend fun closeBot(): Response<Boolean> =
        client.post("close").body()

    // --- Messages (additional) ---

    override suspend fun sendVenue(params: SendVenueRequest): Response<Message> =
        client.post("sendVenue") { setBody(params) }.body()

    // --- Editing (additional) ---

    override suspend fun editMessageMedia(params: EditMessageMediaRequest): Response<EditMessageResult> =
        client.post("editMessageMedia") { setBody(params) }.body()

    override suspend fun editMessageLiveLocation(params: EditMessageLiveLocationRequest): Response<EditMessageResult> =
        client.post("editMessageLiveLocation") { setBody(params) }.body()

    override suspend fun stopMessageLiveLocation(params: StopMessageLiveLocationRequest): Response<EditMessageResult> =
        client.post("stopMessageLiveLocation") { setBody(params) }.body()

    override suspend fun setMessageReaction(params: SetMessageReactionRequest): Response<Boolean> =
        client.post("setMessageReaction") { setBody(params) }.body()

    override suspend fun stopPoll(params: StopPollRequest): Response<Poll> =
        client.post("stopPoll") { setBody(params) }.body()

    // --- Chat management (additional) ---

    override suspend fun setChatPermissions(params: SetChatPermissionsRequest): Response<Boolean> =
        client.post("setChatPermissions") { setBody(params) }.body()

    override suspend fun setChatAdministratorCustomTitle(params: SetChatAdministratorCustomTitleRequest): Response<Boolean> =
        client.post("setChatAdministratorCustomTitle") { setBody(params) }.body()

    override suspend fun banChatSenderChat(params: BanChatSenderChatRequest): Response<Boolean> =
        client.post("banChatSenderChat") { setBody(params) }.body()

    override suspend fun unbanChatSenderChat(params: UnbanChatSenderChatRequest): Response<Boolean> =
        client.post("unbanChatSenderChat") { setBody(params) }.body()

    override suspend fun deleteChatPhoto(params: DeleteChatPhotoRequest): Response<Boolean> =
        client.post("deleteChatPhoto") { setBody(params) }.body()

    override suspend fun unpinAllChatMessages(params: UnpinAllChatMessagesRequest): Response<Boolean> =
        client.post("unpinAllChatMessages") { setBody(params) }.body()

    override suspend fun editChatInviteLink(params: EditChatInviteLinkRequest): Response<ChatInviteLink> =
        client.post("editChatInviteLink") { setBody(params) }.body()

    override suspend fun revokeChatInviteLink(params: RevokeChatInviteLinkRequest): Response<ChatInviteLink> =
        client.post("revokeChatInviteLink") { setBody(params) }.body()

    override suspend fun getUserProfilePhotos(params: GetUserProfilePhotosRequest): Response<UserProfilePhotos> =
        client.post("getUserProfilePhotos") { setBody(params) }.body()

    override suspend fun getUserChatBoosts(params: GetUserChatBoostsRequest): Response<UserChatBoosts> =
        client.post("getUserChatBoosts") { setBody(params) }.body()

    override suspend fun getBusinessConnection(params: GetBusinessConnectionRequest): Response<BusinessConnection> =
        client.post("getBusinessConnection") { setBody(params) }.body()

    // --- Forum Topics (additional) ---

    override suspend fun editForumTopic(params: EditForumTopicRequest): Response<Boolean> =
        client.post("editForumTopic") { setBody(params) }.body()

    override suspend fun closeForumTopic(params: CloseForumTopicRequest): Response<Boolean> =
        client.post("closeForumTopic") { setBody(params) }.body()

    override suspend fun reopenForumTopic(params: ReopenForumTopicRequest): Response<Boolean> =
        client.post("reopenForumTopic") { setBody(params) }.body()

    override suspend fun deleteForumTopic(params: DeleteForumTopicRequest): Response<Boolean> =
        client.post("deleteForumTopic") { setBody(params) }.body()

    override suspend fun unpinAllForumTopicMessages(params: UnpinAllForumTopicMessagesRequest): Response<Boolean> =
        client.post("unpinAllForumTopicMessages") { setBody(params) }.body()

    // --- Commands (additional) ---

    override suspend fun setMyName(params: SetMyNameRequest): Response<Boolean> =
        client.post("setMyName") { setBody(params) }.body()

    override suspend fun getMyName(params: GetMyNameRequest): Response<BotName> =
        client.post("getMyName") { setBody(params) }.body()

    override suspend fun setMyDescription(params: SetMyDescriptionRequest): Response<Boolean> =
        client.post("setMyDescription") { setBody(params) }.body()

    override suspend fun getMyDescription(params: GetMyDescriptionRequest): Response<BotDescription> =
        client.post("getMyDescription") { setBody(params) }.body()

    override suspend fun setMyShortDescription(params: SetMyShortDescriptionRequest): Response<Boolean> =
        client.post("setMyShortDescription") { setBody(params) }.body()

    override suspend fun getMyShortDescription(params: GetMyShortDescriptionRequest): Response<BotShortDescription> =
        client.post("getMyShortDescription") { setBody(params) }.body()

    override suspend fun setMyDefaultAdministratorRights(params: SetMyDefaultAdministratorRightsRequest): Response<Boolean> =
        client.post("setMyDefaultAdministratorRights") { setBody(params) }.body()

    override suspend fun getMyDefaultAdministratorRights(params: GetMyDefaultAdministratorRightsRequest): Response<ChatAdministratorRights> =
        client.post("getMyDefaultAdministratorRights") { setBody(params) }.body()

    override suspend fun setChatMenuButton(params: SetChatMenuButtonRequest): Response<Boolean> =
        client.post("setChatMenuButton") { setBody(params) }.body()

    override suspend fun getChatMenuButton(params: GetChatMenuButtonRequest): Response<MenuButton> =
        client.post("getChatMenuButton") { setBody(params) }.body()

    // --- Payments (additional) ---

    override suspend fun answerPreCheckoutQuery(params: AnswerPreCheckoutQueryRequest): Response<Boolean> =
        client.post("answerPreCheckoutQuery") { setBody(params) }.body()

    override suspend fun answerShippingQuery(params: AnswerShippingQueryRequest): Response<Boolean> =
        client.post("answerShippingQuery") { setBody(params) }.body()

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
public fun TelegramBot(
    token: String,
    engine: HttpClientEngine? = null,
    configure: HttpClientConfig<*>.() -> Unit = {},
): Bot = BotImp(token, engine, configure)

/** Extra seconds added to the HTTP request timeout on top of the getUpdates long-poll timeout. */
private const val LONG_POLL_GRACE_SECONDS = 10L
