package com.nikitakrapo.utils.exceptions

import io.ktor.server.plugins.ContentTransformationException

class DtoParsingException(
    override val message: String,
    override val cause: Throwable? = null,
) : ContentTransformationException(message)