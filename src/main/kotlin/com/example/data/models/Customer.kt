package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer (
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)

val customers = mutableListOf(
    Customer("1", "Maspam", "lastname", "emials@gmailc.om")
)