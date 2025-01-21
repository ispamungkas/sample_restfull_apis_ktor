package com.example.security.token

data class TokenConfig(
    val issuer: String?,
    val audience: String?,
    val expiredIn: Long?,
    val secret: String?
)
