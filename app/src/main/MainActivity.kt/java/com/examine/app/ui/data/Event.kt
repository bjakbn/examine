package com.examine.app.data

data class Event(
    val time: Long,
    val type: String, // READ / WRITE / MODIFY
    val packageName: String,
    val path: String
)
