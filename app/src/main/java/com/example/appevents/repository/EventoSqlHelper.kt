package com.example.appevents.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EventoSqlHelper(context: Context):
SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME(" +
                    "$COLUMN_ID INTEGER PRIMARY KEY, " +
                    "$COLUMN_TITULO TEXT, " +
                    "$COLUMN_DESCRICAO TEXT, " +
                    "$COLUMN_INICIO INTEGER, " +
                    "$COLUMN_LOCAL TEXT, " +
                    "$COLUMN_LAT_LOCAL FLOAT, " +
                    "$COLUMN_LNG_LOCAL FLOAT, " +
                    "$COLUMN_CARGA_HORARIA INTEGER, " +
                    "$COLUMN_QTD_VAGAS INTEGER, " +
                    "$COLUMN_ID_TIPO INTEGER);\n " +
                    "CREATE TABLE $TABLE_TIPONOME(" +
                    "$COLUMN_TIPODESCRICAO TEXT, " +
                    "$COLUMN_TIPOID INTEGER PRIMARY KEY);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}