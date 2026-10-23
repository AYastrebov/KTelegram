package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/** This object represents a portion of the price for goods or services. */
@Serializable
public data class LabeledPrice(
    /** Portion label. */
    val label: String,

    /** Price of the product in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. */
    val amount: Int,
)

/** This object represents one shipping option. */
@Serializable
public data class ShippingOption(
    /** Shipping option identifier. */
    val id: String,

    /** Option title. */
    val title: String,

    /** List of price portions. */
    val prices: List<LabeledPrice>,
)

/** This object represents a shipping address. */
@Serializable
public data class ShippingAddress(
    /** Two-letter ISO 3166-1 alpha-2 country code. */
    @SerialName("country_code")
    val countryCode: String,

    /** State, if applicable. */
    val state: String,

    /** City. */
    val city: String,

    /** First line for the address. */
    @SerialName("street_line1")
    val streetLine1: String,

    /** Second line for the address. */
    @SerialName("street_line2")
    val streetLine2: String,

    /** Address post code. */
    @SerialName("post_code")
    val postCode: String,
)

/** This object represents information about an order. */
@Serializable
public data class OrderInfo(
    /** User name. */
    val name: String? = null,

    /** User's phone number. */
    @SerialName("phone_number")
    val phoneNumber: String? = null,

    /** User email. */
    val email: String? = null,

    /** User shipping address. */
    @SerialName("shipping_address")
    val shippingAddress: ShippingAddress? = null,
)

/** This object contains information about an incoming shipping query. */
@Serializable
public data class ShippingQuery(
    /** Unique query identifier. */
    val id: String,

    /** User who sent the query. */
    val from: User,

    /** Bot-specified invoice payload. */
    @SerialName("invoice_payload")
    val invoicePayload: String,

    /** User specified shipping address. */
    @SerialName("shipping_address")
    val shippingAddress: ShippingAddress,
)

/** This object contains information about an incoming pre-checkout query. */
@Serializable
public data class PreCheckoutQuery(
    /** Unique query identifier. */
    val id: String,

    /** User who sent the query. */
    val from: User,

    /** Three-letter ISO 4217 currency code, or “XTR” for payments in Telegram Stars. */
    val currency: String,

    /** Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. */
    @SerialName("total_amount")
    val totalAmount: Int,

    /** Bot-specified invoice payload. */
    @SerialName("invoice_payload")
    val invoicePayload: String,

    /** Identifier of the shipping option chosen by the user. */
    @SerialName("shipping_option_id")
    val shippingOptionId: String? = null,

    /** Order information provided by the user. */
    @SerialName("order_info")
    val orderInfo: OrderInfo? = null,
)

/** This object contains basic information about a successful payment. Note that if the buyer initiates a chargeback with the relevant payment provider following this transaction, the funds may be debited from your balance. This is outside of Telegram's control. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class SuccessfulPayment(
    /** Three-letter ISO 4217 currency code, or “XTR” for payments in Telegram Stars. */
    val currency: String,

    /** Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. */
    @SerialName("total_amount")
    val totalAmount: Int,

    /** Bot-specified invoice payload. */
    @SerialName("invoice_payload")
    val invoicePayload: String,

    /** Telegram payment identifier. */
    @SerialName("telegram_payment_charge_id")
    val telegramPaymentChargeId: String,

    /** Provider payment identifier. */
    @SerialName("provider_payment_charge_id")
    val providerPaymentChargeId: String,

    /** Expiration date of the subscription, in Unix time; for recurring payments only. */
    @SerialName("subscription_expiration_date")
    @Serializable(with = InstantUnixSerializer::class)
    val subscriptionExpirationDate: kotlin.time.Instant? = null,

    /** True, if the payment is a recurring payment for a subscription. */
    @SerialName("is_recurring")
    val isRecurring: Boolean? = null,

    /** True, if the payment is the first payment for a subscription. */
    @SerialName("is_first_recurring")
    val isFirstRecurring: Boolean? = null,

    /** Identifier of the shipping option chosen by the user. */
    @SerialName("shipping_option_id")
    val shippingOptionId: String? = null,

    /** Order information provided by the user. */
    @SerialName("order_info")
    val orderInfo: OrderInfo? = null,
)

/** This object contains basic information about a refunded payment. */
@Serializable
public data class RefundedPayment(
    /** Three-letter ISO 4217 currency code, or “XTR” for payments in Telegram Stars. */
    val currency: String,

    /** Total refunded price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45, total_amount = 145. */
    @SerialName("total_amount")
    val totalAmount: Int,

    /** Bot-specified invoice payload. */
    @SerialName("invoice_payload")
    val invoicePayload: String,

    /** Telegram payment identifier. */
    @SerialName("telegram_payment_charge_id")
    val telegramPaymentChargeId: String,

    /** Provider payment identifier. */
    @SerialName("provider_payment_charge_id")
    val providerPaymentChargeId: String? = null,
)

/** This object contains basic information about an invoice. */
@Serializable
public data class Invoice(
    /** Product name. */
    val title: String,

    /** Product description. */
    val description: String,

    /** Unique bot deep-linking parameter that can be used to generate this invoice. */
    @SerialName("start_parameter")
    val startParameter: String,

    /** Three-letter ISO 4217 currency code, or “XTR” for payments in Telegram Stars. */
    val currency: String,

    /** Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. */
    @SerialName("total_amount")
    val totalAmount: Int,
)

/** This object contains information about a paid media purchase. */
@Serializable
public data class PaidMediaPurchased(
    /** User who purchased the media. */
    val from: User,

    /** Bot-specified paid media payload. */
    @SerialName("paid_media_payload")
    val paidMediaPayload: String,
)

/** This object contains information about changes to a user payment subscription toward the current bot. */
@Serializable
public data class BotSubscriptionUpdated(
    /** User who subscribed for payments toward the bot. */
    val user: User,

    /** Bot-specified invoice payload. */
    @SerialName("invoice_payload")
    val invoicePayload: String,

    /** The new state of the subscription. */
    val state: String,
)
