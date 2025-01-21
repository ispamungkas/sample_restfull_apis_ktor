package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    val jwtAudience = "jwt.audience"
    val jwtDomain = "jwt.domain"
    val jwtRealm = "jwt.realm"
    val jwtSecret = "secret"
    authentication {
        jwt {
            val environment = this@configureSecurity.environment
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(environment.config.property(jwtAudience).getString())
                    .withIssuer(environment.config.property(jwtDomain).getString())
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(
                        environment.config.property(jwtAudience).getString()
                    )
                ) JWTPrincipal(credential.payload) else null
            }
        }
    }
}
