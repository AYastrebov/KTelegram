package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.ShippingOption
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [answerPreCheckoutQuery][com.github.ayastrebov.telegram.Bot.answerPreCheckoutQuery].
 */
@Serializable
public data class AnswerPreCheckoutQueryRequest(
    /** Unique identifier for the query to be answered. */
    @SerialName("pre_checkout_query_id")
    val preCheckoutQueryId: String,

    /** Specify True if everything is alright (goods are available, etc.) and the bot is ready to proceed with the order. */
    val ok: Boolean,

    /** Required if ok is False. */
    @SerialName("error_message")
    val errorMessage: String? = null,
)

/**
 * Parameters for [answerShippingQuery][com.github.ayastrebov.telegram.Bot.answerShippingQuery].
 */
@Serializable
public data class AnswerShippingQueryRequest(
    /** Unique identifier for the query to be answered. */
    @SerialName("shipping_query_id")
    val shippingQueryId: String,

    /** Pass True if delivery to the specified address is possible and False if there are any problems (for example, if delivery to the specified address is not possible). */
    val ok: Boolean,

    /** Required if ok is True. */
    @SerialName("shipping_options")
    val shippingOptions: List<ShippingOption>? = null,

    /** Required if ok is False. */
    @SerialName("error_message")
    val errorMessage: String? = null,
)
