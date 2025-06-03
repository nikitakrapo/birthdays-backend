package com.nikitakrapo.plugins.security

import com.nikitakrapo.utils.exceptions.UnauthorizedException
import io.ktor.server.auth.principal
import io.ktor.server.routing.RoutingContext

inline fun <reified T : Any> RoutingContext.requirePrincipal(): T {
    return call.principal<T>() ?: throw UnauthorizedException(message = "No principal found")
}