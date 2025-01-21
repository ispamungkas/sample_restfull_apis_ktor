package com.example.routes

import com.example.data.models.Customer
import com.example.data.models.customers
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
    route("/customer") {
        get {
            if (customers.isNotEmpty()) {
                call.respond(customers)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }

        // Get customer with params id
        get("{id?}") {
            // Get id
            val param = call.parameters["id"] ?: return@get call.respondText("Missing IDs", status = HttpStatusCode.BadRequest)
            val customer = customers.find { it.id == param } ?: return@get call.respondText("No customer found $param", status = HttpStatusCode.NotFound)
            call.respond(customer)
        }

        // Insert customer into Database
        post {
            val customer = call.receive<Customer>()
            customers.add(customer)
            call.respond(message = "Customer Added", status = HttpStatusCode.Created)
        }
    }
}