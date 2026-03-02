package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.InputMessageContent
import com.github.ayastrebov.telegram.model.KeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryRequest(
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

@Serializable
sealed class InlineQueryResult {
    abstract val id: String

    @SerialName("input_message_content")
    abstract val inputMessageContent: InputMessageContent

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