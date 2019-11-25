package com.example.appevents.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.appevents.model.EventoDto
import com.example.appevents.model.TipoEventoDto

class SQLiteRepository(context: Context): EventoRepository{

    private val helper: EventoSqlHelper = EventoSqlHelper(context)

    private fun insert(eventoDto: EventoDto){
        val db = helper.writableDatabase

        val cv = ContentValues().apply {
            put(COLUMN_ID, eventoDto.id)
            put(COLUMN_TITULO, eventoDto.titulo)
            put(COLUMN_DESCRICAO, eventoDto.descricao)
            put(COLUMN_INICIO, eventoDto.inicioEvento)
            put(COLUMN_LOCAL, eventoDto.localizacao)
            put(COLUMN_LAT_LOCAL, eventoDto.latLocalizacao)
            put(COLUMN_LNG_LOCAL, eventoDto.lngLocalizacao)
            put(COLUMN_CARGA_HORARIA, eventoDto.cargaHoraria)
            put(COLUMN_QTD_VAGAS, eventoDto.qtdVagas)
            put(COLUMN_ID_TIPO, eventoDto.idTipoEvento)

        }

        db.insert(TABLE_NAME, null, cv)

        db.close()
    }

    override fun save(eventoDto: EventoDto) {
        insert(eventoDto)
    }

    override fun saveTipo(tipoEventoDto: TipoEventoDto) {
        val db = helper.writableDatabase

        val cv = ContentValues().apply {
            put(COLUMN_TITULO, tipoEventoDto.descricao)
            put(COLUMN_ID, tipoEventoDto.idTipo)
        }

        db.insert(TABLE_TIPONOME, null, cv)

        db.close()
    }

    override fun remove(eventoDto: EventoDto) {
        val db = helper.writableDatabase

        db.delete(
            TABLE_NAME,
            "$COLUMN_ID = ? ",
            arrayOf(eventoDto.id.toString())
        )

        db.close()
    }

    fun cleanTables(){
        val db = helper.writableDatabase

        db.execSQL("delete from "+ TABLE_NAME)

        db.close()
    }

    override fun list(callback: (MutableList<EventoDto>) -> Unit) {
        var sql = "SELECT * FROM $TABLE_NAME"
        var args: Array<String>? = null

        sql += " ORDER BY $COLUMN_ID"
        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, args)
        val eventos = ArrayList<EventoDto>()
        while (cursor.moveToNext()){
            val evento = eventoDtoFromCursor(cursor)
            eventos.add(evento)
        }
        cursor.close()
        db.close()

        callback(eventos)
    }

    private fun getTipoDescricao(idTipo:Int):String{
        var sql = "SELECT t WHERE ($COLUMN_TIPOID == $idTipo) FROM $TABLE_TIPONOME" +
                "ORDER BY $COLUMN_ID;"
        var args: Array<String>? = null

        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, args)

        return nomeTipoFromCursor(cursor)
    }

    private fun nomeTipoFromCursor(cursor: Cursor):String {
        return cursor.getString(cursor.getColumnIndex(COLUMN_TIPODESCRICAO))
    }

    private fun eventoDtoFromCursor(cursor: Cursor): EventoDto {
        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
        val titulo = cursor.getString(cursor.getColumnIndex(COLUMN_TITULO))
        val descricao = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO))
        val inicioEvento = cursor.getLong(cursor.getColumnIndex(COLUMN_INICIO))
        val localizacao = cursor.getString(cursor.getColumnIndex(COLUMN_LOCAL))
        val latLocalizacao = cursor.getFloat(cursor.getColumnIndex(COLUMN_LAT_LOCAL))
        val lngLocalizacao = cursor.getFloat(cursor.getColumnIndex(COLUMN_LNG_LOCAL))
        val cargaHoraria = cursor.getInt(cursor.getColumnIndex(COLUMN_CARGA_HORARIA))
        val qtdVagas = cursor.getInt(cursor.getColumnIndex(COLUMN_QTD_VAGAS))
        val idTipoEvento = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TIPO))

        var evento = EventoDto(id, titulo, descricao, inicioEvento, localizacao,
            latLocalizacao, lngLocalizacao, cargaHoraria, qtdVagas, idTipoEvento)
        evento.tipo = getTipoDescricao(idTipoEvento)
        return evento
    }
}