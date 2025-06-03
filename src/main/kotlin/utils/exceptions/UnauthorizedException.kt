package com.nikitakrapo.utils.exceptions

class UnauthorizedException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : IllegalStateException()