package com.example.appevents.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appevents.R
import com.example.appevents.adapter.EventsListAdapter
import com.example.appevents.commun.*
import com.example.appevents.commun.PreferenceHelper.edit
import com.example.appevents.fragment.FragmentEventsList
import com.example.appevents.fragment.FragmentMap
import com.example.appevents.model.EventoDto
import com.example.appevents.model.TokenResponseModel
import com.example.appevents.network.ApiService
import com.example.appevents.repository.SQLiteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var eventos: MutableList<EventoDto>? = null
    private var eventoRepository:SQLiteRepository? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PreferenceHelper.defaultPrefs(this)
        val accessToken: String? = prefs[PREF_API_ACCESS_TOKEN]

        /*
        if (!Util.isNetworkAvailable()) {
            Toast.makeText(this, getString(R.string.internet_connection_error), Toast.LENGTH_LONG).show()
            return
        }

         */

        if (accessToken.isNullOrEmpty()) {
            getToken()
        }
        Log.e("Evento1", "criando service")
        startService(Intent(this,
            MyIntentService::class.java))
        Log.e("Evento12", "criado service")
        eventoRepository = SQLiteRepository(this)

        updateList()

    }

    /*
            Configurações do actionBar
     */
    //Criação
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (eventos == null) {
            Toast.makeText(applicationContext, "Eventos não encontrados", Toast.LENGTH_SHORT).show()
            return false
        }
        when (item?.itemId) {
            R.id.navigation_home -> {
                Toast.makeText(this, "TODO", Toast.LENGTH_LONG).show()
            }
            R.id.navigation_dashboard -> {
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, FragmentMap())
                transaction.addToBackStack(null)
                transaction.commit()
                return true
            }
            R.id.navigation_notifications -> {
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, FragmentEventsList() as Fragment)
                transaction.addToBackStack(null)
                transaction.commit()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun list(eventos:MutableList<EventoDto>){
        this.eventos  = eventos
    }

    fun updateList(){
        eventoRepository?.list { list(it) }
    }

    private fun getToken() {
        val tokenCall = ApiService.instance.getToken(CLIENT_ID, 
                    CLIENT_SECRET, GRANT_TYPE)
        tokenCall.enqueue(object :Callback<TokenResponseModel>{
            override fun onFailure(call: Call<TokenResponseModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, getString(R.string.handle_api_token_error),
                    Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<TokenResponseModel>,
                response: Response<TokenResponseModel>
            ) {
                val prefs = PreferenceHelper.defaultPrefs(applicationContext)
                Log.e("token_acess", response.body()?.accessToken)
                prefs[PREF_API_ACCESS_TOKEN] = response.body()?.accessToken.toString()
                prefs[PREF_API_TOKEN_TYPE] = response.body()?.tokenType.toString()

                prefs[PREF_API_REFRESH_TOKEN] = response.body()?.refreshToken.toString()
                prefs[PREF_API_EXPIRES_IN] = response.body()?.expiresIn.toString()
                prefs[PREF_API_SCOPE] = response.body()?.scope.toString()
            }

        })
    }


}

private operator fun SharedPreferences.set(tokenType: String, value: String) {
    when (value) {
        is String? -> edit({it.putString(tokenType, value) })
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}


