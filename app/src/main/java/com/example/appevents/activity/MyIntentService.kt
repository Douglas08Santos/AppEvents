package com.example.appevents.activity

import android.app.IntentService
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.appevents.R
import com.example.appevents.commun.*
import com.example.appevents.model.EventoDto
import com.example.appevents.model.SearchEventResponseModel
import com.example.appevents.model.SearchTypeResponseModel
import com.example.appevents.model.TipoEventoDto
import com.example.appevents.network.ApiService
import com.example.appevents.repository.SQLiteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyIntentService:IntentService("ServiceEvento") {
    private var eventoRepository: SQLiteRepository? = null
    private var TAG = "DouglasS"
    override fun onHandleIntent(intent: Intent?) {
        eventoRepository = SQLiteRepository(this)
        getEvento()
    }

    private fun getEvento() {
        eventoRepository?.cleanTables()
        Log.e(TAG,"get Evento")

        val prefs = PreferenceHelper.defaultPrefs(this)
        val accessToken: String? = prefs[PREF_API_ACCESS_TOKEN]
        Log.e(TAG, accessToken)
        val searchEventCall = ApiService.instanceGet
            .getEventsList(
                "bearer $accessToken", "$X_API_KEY")
        val searchTypeCall = ApiService.instanceGet
            .getTypeList(
                "bearer $accessToken", "$X_API_KEY")

        //Get events of API
        searchEventCall.enqueue(object : Callback<List<EventoDto>> {

            override fun onFailure(call: Call<List<EventoDto>>, t: Throwable) {
                Log.e(TAG,
                    "get evento on fail${t.message.toString()}")
            }

            override fun onResponse(
                call: Call<List<EventoDto>>,
                response: Response<List<EventoDto>>
            ) {
                var eventos = response.body()

                for (evento in eventos!! ){
                    evento.tipo = ""
                    Log.e(TAG, evento.id.toString())
                    Log.e(TAG, evento.titulo)
                    Log.e(TAG, evento.descricao)
                    Log.e(TAG, evento.inicioEvento.toString())
                    Log.e(TAG, evento.localizacao)
                    Log.e(TAG, evento.latLocalizacao.toString())
                    Log.e(TAG, evento.lngLocalizacao.toString())
                    Log.e(TAG, evento.cargaHoraria.toString())
                    Log.e(TAG, evento.qtdVagas.toString())
                    Log.e(TAG, evento.idTipoEvento.toString())
                    eventoRepository?.save(evento)
                }
                Log.e(TAG, "get evento on response ${response.body()
                    ?.get(0)?.titulo.toString()}")
            }
        })
        //Get types of API
        searchTypeCall.enqueue(object :Callback<List<TipoEventoDto>>{
            override fun onFailure(call: Call<List<TipoEventoDto>>, t: Throwable) {
                Log.e(TAG,"get TYPE on fail${t.message.toString()}")
            }

            override fun onResponse(
                call: Call<List<TipoEventoDto>>,
                response: Response<List<TipoEventoDto>>
            ) {
                var tipos = response.body()
                Log.e(TAG, "Tipo ONFail")
                for (tipo in tipos!! ){
                    Log.e(TAG, tipo.idTipo.toString())
                    Log.e(TAG, tipo.descricao)
                    eventoRepository?.saveTipo(tipo)
                }
                Log.e(TAG,
                    response.body()?.get(0)?.descricao.toString())
            }


        })
    }
}

internal inline operator fun <reified T : Any> SharedPreferences
        .get(key: String, defaultValue: T? = null): T?{
    return when (T::class) {
        kotlin.String::class -> getString(key, defaultValue as? String) as T?
        kotlin.Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        kotlin.Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        kotlin.Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        kotlin.Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}


