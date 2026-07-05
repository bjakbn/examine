package com.examine.app.data

object EventStore {
    val events = mutableListOf<Event>()

    fun add(event: Event) {
        events.add(event)
    }

    fun clear() {
        events.clear()
    }

    fun getAll(): List<Event> = events
}
