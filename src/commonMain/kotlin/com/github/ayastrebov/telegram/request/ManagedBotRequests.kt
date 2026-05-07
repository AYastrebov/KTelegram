package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.KeyboardButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [getManagedBotToken][com.github.ayastrebov.telegram.Bot.getManagedBotToken].
 */
@Serializable
public data class GetManagedBotTokenRequest(
    @SerialName("user_id")
    val userId: Long,
)

/**
 * Parameters for [replaceManagedBotToken][com.github.ayastrebov.telegram.Bot.replaceManagedBotToken].
 */
@Serializable
public data class ReplaceManagedBotTokenRequest(
    @SerialName("user_id")
    val userId: Long,
)

/**
 * Parameters for [savePreparedKeyboardButton][com.github.ayastrebov.telegram.Bot.savePreparedKeyboardButton].
 */
@Serializable
public data class SavePreparedKeyboardButtonRequest(
    @SerialName("user_id")
    val userId: Long,

    val button: KeyboardButton,
)

/**
 * Parameters for [setMyProfilePhoto][com.github.ayastrebov.telegram.Bot.setMyProfilePhoto].
 */
@Serializable
public data class SetMyProfilePhotoRequest(
    val photo: String,
)
