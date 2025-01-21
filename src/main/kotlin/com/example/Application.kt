package com.example

import com.example.data.models.MongoDataSource
import com.example.data.models.User
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val password = System.getenv("DB_PASSWORD")
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://maspam:$password@tester.zdds8.mongodb.net/?retryWrites=true&w=majority&appName=Tester"
    ).coroutine
        .getDatabase("sample_database")
    val userDataSource = MongoDataSource(db)
    GlobalScope.launch {
//        val user = User(
//            username = "Maspam",
//            password = "maspam",
//            salt = "salt",
//            email = "maspma@gmail.com"
//        )
//        userDataSource.insertUser(user)
    }

    configureSecurity()
    configureSerialization()
    configureRouting()
    configureMonitoring()
}
