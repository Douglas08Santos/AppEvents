package com.example.appevents.repository

import com.example.appevents.model.EventoDto
import com.example.appevents.model.TipoEventoDto

interface EventoRepository {
    fun save(eventoDto: EventoDto)
    fun saveTipo(tipoEventoDto: TipoEventoDto)
    fun remove(eventoDto: EventoDto)
    fun list(callback:(MutableList<EventoDto>) -> Unit)
}