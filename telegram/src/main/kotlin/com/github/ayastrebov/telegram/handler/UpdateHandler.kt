package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.Handler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class UpdateHandler : Handler {
    val logger: Logger = LoggerFactory.getLogger(javaClass)
}