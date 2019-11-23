package com.example.appevents.repository

import com.example.appevents.model.EventoDto

interface EventoRepository {
    fun save(eventoDto: EventoDto)
    fun remove(eventoDto: EventoDto)
    fun list(callback:(MutableList<EventoDto>) -> Unit)
}