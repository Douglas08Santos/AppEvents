package com.example.appevents.util

import android.os.AsyncTask
import com.example.appevents.commun.BASE_URL
import com.example.appevents.commun.CLIENT_ID
import com.example.appevents.commun.CLIENT_SECRET
import java.io.IOException

class ServiceTask: AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        try {
            var clientCredentials = ClientCredentials();
            try {
                val token = clientCredentials
                    .getClientCredentials(BASE_URL, CLIENT_ID, CLIENT_SECRET)
            }catch (e:IOException){
                e.printStackTrace()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        return null
    }
}