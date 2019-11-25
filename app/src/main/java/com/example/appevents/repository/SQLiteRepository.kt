package com.example.appevents.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.appevents.model.EventoDto

class SQLiteRepository(context: Context): EventoRepository{

    private val helper: EventoSqlHelper = EventoSqlHelper(context)

    private fun insert(eventoDto: EventoDto){
        val db = helper.writableDatabase

        val cv = ContentValues().apply {
            put(COLUMN_ID, eventoDto.id)
            put(COLUMN_TITULO, eventoDto.titulo)
            put(COLUMN_DESCRICAO, eventoDto.descricao)
            put(COLUMN_INICIO, eventoDto.inicioEvento)
            put(COLUMN_FIM_EVENTO, eventoDto.fimEvento)
            put(COLUMN_LOCAL, eventoDto.localizacao)
            put(COLUMN_LAT_LOCAL, eventoDto.latLocalizacao)
            put(COLUMN_LNG_LOCAL, eventoDto.lngLocalizacao)
            put(COLUMN_CARGA_HORARIA, eventoDto.cargaHoraria)
            put(COLUMN_QTD_VAGAS, eventoDto.qtdVagas)
            put(COLUMN_ID_TIPO, eventoDto.idTipoEvento)
            put(COLUMN_FAVORITE, eventoDto.favorite)

        }

        db.insert(TABLE_NAME, null, cv)

        db.close()
    }

    override fun save(eventoDto: EventoDto) {
        insert(eventoDto)
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

        db.execSQL("delete from "+ TABLE_NAME);

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

    private fun eventoDtoFromCursor(cursor: Cursor): EventoDto {
        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
        val titulo = cursor.getString(cursor.getColumnIndex(COLUMN_TITULO))
        val descricao = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO))
        val inicioEvento = cursor.getInt(cursor.getColumnIndex(COLUMN_INICIO))
        val fimEvento = cursor.getInt(cursor.getColumnIndex(COLUMN_FIM_EVENTO))
        val localizacao = cursor.getString(cursor.getColumnIndex(COLUMN_LOCAL))
        val latLocalizacao = cursor.getInt(cursor.getColumnIndex(COLUMN_LAT_LOCAL))
        val lngLocalizacao = cursor.getInt(cursor.getColumnIndex(COLUMN_LNG_LOCAL))
        val cargaHoraria = cursor.getInt(cursor.getColumnIndex(COLUMN_CARGA_HORARIA))
        val qtdVagas = cursor.getInt(cursor.getColumnIndex(COLUMN_QTD_VAGAS))
        val idTipoEvento = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TIPO))

        var evento = EventoDto(id, titulo, descricao, inicioEvento, fimEvento, localizacao,
            latLocalizacao, lngLocalizacao, cargaHoraria, qtdVagas, idTipoEvento)
        evento.favorite = cursor.getInt(cursor.getColumnIndex(COLUMN_FAVORITE))
        return evento
    }
}