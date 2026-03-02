package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.InputMessageContent
import com.github.ayastrebov.telegram.model.KeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [answerInlineQuery][com.github.ayastrebov.telegram.Bot.answerInlineQuery].
 */
@Serializable
data class AnswerInlineQueryRequest(
    @SerialName("inline_query_id")
    val inlineQueryId: String,

    val results: List<InlineQueryResult> = emptyList(),

    @SerialName("cache_time")
    val cacheTime: Int? = null,

    @SerialName("is_personal")
    val isPersonal: Boolean? = null,

    @SerialName("next_offset")
    val nextOffset: String? = null,
)

/**
 * Parameters for [answerCallbackQuery][com.github.ayastrebov.telegram.Bot.answerCallbackQuery].
 */
@Serializable
data class AnswerCallbackQueryRequest(
    @SerialName("callback_query_id")
    val callbackQueryId: String,

    val text: String? = null,

    @SerialName("show_alert")
    val showAlert: Boolean? = null,

    val url: String? = null,

    @SerialName("cache_time")
    val cacheTime: Int? = null,
)

/**
 * Base type for inline query results. Each subclass maps to a specific result type.
 */
@Serializable
sealed class InlineQueryResult {
    abstract val id: String

    @SerialName("input_message_content")
    abstract val inputMessageContent: InputMessageContent

    /**
     * Represents an article result for an inline query.
     */
    @Serializable
    @SerialName("article")
    class InlineQueryResultArticle(
        override val id: String,

        @SerialName("input_message_content")
        override val inputMessageContent: InputMessageContent,

        val title: String,

        @SerialName("reply_markup")
        val replyMarkup: KeyboardMarkup? = null,

        val description: String? = null,
        val url: String? = null,

        @SerialName("thumbnail_url")
        val thumbnailUrl: String? = null,

        @SerialName("thumbnail_width")
        val thumbnailWidth: Int? = null,

        @SerialName("thumbnail_height")
        val thumbnailHeight: Int? = null,
    ) : InlineQueryResult()
}
