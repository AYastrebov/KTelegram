package com.github.ayastrebov.telegram.model

import kotlinx.serialization.Serializable

/** This object represents a story. */
@Serializable
public data class Story(
    /** Chat that posted the story. */
    val chat: Chat,

    /** Unique identifier for the story in the chat. */
    val id: Long,
)
