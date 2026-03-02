package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.BotCommand
import kotlinx.serialization.Serializable

/**
 * Parameters for [setMyCommands][com.github.ayastrebov.telegram.Bot.setMyCommands].
 */
@Serializable
data class SetMyCommandsRequest(
    val commands: List<BotCommand>,
)
