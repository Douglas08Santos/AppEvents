package com.example.appevents.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EventoSqlHelper(context: Context):
SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME(" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_CARGA_HORARIA INTEGER, " +
                    "$COLUMN_DESCRICAO TEXT" +
                    "$COLUMN_FIM_EVENTO INTEGER, " +
                    "$COLUMN_ID_TIPO INTEGER, " +
                    "$COLUMN_INICIO INTEGER, " +
                    "$COLUMN_LAT_LOCAL INTEGER, " +
                    "$COLUMN_LNG_LOCAL INTEGER, " +
                    "$COLUMN_LOCAL TEXT, " +
                    "$COLUMN_QTD_VAGAS INTEGER, " +
                    "$COLUMN_TITULO TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}