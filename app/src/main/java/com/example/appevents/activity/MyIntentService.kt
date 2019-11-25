package com.example.appevents.activity

import android.app.IntentService
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.appevents.R
import com.example.appevents.commun.*
import com.example.appevents.model.SearchResponseModel
import com.example.appevents.network.ApiService
import com.example.appevents.repository.SQLiteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyIntentService:IntentService("ServiceEvento") {
    private var eventoRepository: SQLiteRepository? = null

    override fun onHandleIntent(intent: Intent?) {
        Log.e("Evento13", "no service")
        eventoRepository = SQLiteRepository(this)
        getEvento()
    }

    private fun getEvento() {
        //eventoRepository?.cleanTables()
        if (!Util.isNetworkAvailable()){
            Util.makeToast(this,
                getString(R.string.internet_connection_error))
        }

        val prefs = PreferenceHelper.defaultPrefs(this)
        val accessToken: String? = prefs[PREF_API_ACCESS_TOKEN]
        val searchCall = ApiService.instanceGet
            .getEventsList(
                "bearer $accessToken", "$X_API_KEY")

        searchCall.enqueue(object : Callback<SearchResponseModel> {
            override fun onFailure(call: Call<SearchResponseModel>, t: Throwable) {

                Toast.makeText(applicationContext, getString(R.string.handle_api_token_error),
                    Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<SearchResponseModel>,
                                    response: Response<SearchResponseModel>) {
                var eventos = response.body()?.responseEvent

                for (evento in eventos!! ){
                    eventoRepository?.save(evento)
                }
                Log.d("eventos",
                    response.body()?.responseEvent?.get(0)?.titulo.toString())

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


