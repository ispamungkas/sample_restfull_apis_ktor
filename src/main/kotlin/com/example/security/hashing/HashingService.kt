package com.example.security.hashing

interface HashingService {
    fun generateSaltHash(value: String, saltedLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}