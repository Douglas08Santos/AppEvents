package com.example.appevents.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appevents.R
import com.example.appevents.adapter.EventsListAdapter
import com.example.appevents.commun.*
import com.example.appevents.commun.PreferenceHelper.edit
import com.example.appevents.model.EventoDto
import com.example.appevents.model.TokenResponseModel
import com.example.appevents.network.ApiService
import com.example.appevents.repository.SQLiteRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var TAG = "DouglasA"
    var eventos: MutableList<EventoDto>? = null
    private var eventoRepository = SQLiteRepository(this)
    var adapter:EventsListAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PreferenceHelper.defaultPrefs(this)
        //cleanPrefs()
        val accessToken: String? = prefs[PREF_API_ACCESS_TOKEN]
        Log.e(TAG, "check token")
        if (accessToken.isNullOrEmpty()) {
            Log.e(TAG, "null token")
            getToken()
        }else{
            startService(Intent(this, MyIntentService::class.java))
        }
        //updateList()
        //initRecyclerView()

    }

    private fun initRecyclerView() {
        rvEventsList.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        rvEventsList.layoutManager = layoutManager
    }

    private fun list(eventos:MutableList<EventoDto>){
        this.eventos = eventos
        adapter = EventsListAdapter(eventos)

        rvEventsList.adapter = adapter
    }

    fun updateList(){
        eventoRepository?.list { list(it) }
    }

    fun cleanPrefs(){
        val prefs = PreferenceHelper.defaultPrefs(this)
        prefs[PREF_API_ACCESS_TOKEN] = ""
        prefs[PREF_API_TOKEN_TYPE] = ""

        prefs[PREF_API_REFRESH_TOKEN] = ""
        prefs[PREF_API_EXPIRES_IN] = ""
        prefs[PREF_API_SCOPE] = ""
    }

    /*
            Configurações do actionBar
     */

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
            R.id.navigation_notifications -> {
                initRecyclerView()
                updateList()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun getToken() {
        var TAG = "DouglasAA"
        Log.e(TAG, "Activity get token")

        val tokenCall = ApiService.instanceAuth.getToken(CLIENT_ID,
                    CLIENT_SECRET, GRANT_TYPE)
        tokenCall.enqueue(object :Callback<TokenResponseModel>{
            override fun onFailure(call: Call<TokenResponseModel>, t: Throwable) {
                Log.e(TAG, "onFail get token: ${t.message}")
            }

            override fun onResponse(
                call: Call<TokenResponseModel>,
                response: Response<TokenResponseModel>
            ) {
                Log.e(TAG, "on Success")
                val prefs = PreferenceHelper.defaultPrefs(applicationContext)
                Log.e(TAG, response.body()?.accessToken.toString())
                prefs[PREF_API_ACCESS_TOKEN] = response.body()?.accessToken.toString()
                prefs[PREF_API_TOKEN_TYPE] = response.body()?.tokenType.toString()

                prefs[PREF_API_REFRESH_TOKEN] = response.body()?.refreshToken.toString()
                prefs[PREF_API_EXPIRES_IN] = response.body()?.expiresIn.toString()
                prefs[PREF_API_SCOPE] = response.body()?.scope.toString()
            }

        })


    }


}

operator fun SharedPreferences.set(tokenType: String, value: String) {
    when (value) {
        is String? -> edit({it.putString(tokenType, value) })
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}


