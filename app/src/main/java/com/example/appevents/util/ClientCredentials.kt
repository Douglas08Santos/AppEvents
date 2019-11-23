package com.example.appevents.util

import android.util.Log
import ca.mimic.oauth2library.OAuth2Client
import com.example.appevents.commun.BASE_URL
import com.example.appevents.commun.GRANT_TYPE
import com.example.appevents.commun.OAUTH_TOKEN_SUFFIX
import java.io.IOException


class ClientCredentials {

    @Throws(IOException::class)
    fun getClientCredentials(urlBase: String, clientId: String?,
                             clientSecret: String?): String? {

        val url = BASE_URL + OAUTH_TOKEN_SUFFIX
        val client = OAuth2Client.Builder(clientId, clientSecret, url)
            .grantType(GRANT_TYPE)
            .build()
        val response = client.requestAccessToken()
        if (response.isSuccessful) {
            Log.e("token_acess", response.body)
            return response.accessToken
        }
        throw IOException(response.code.toString() + "," + response.body)
    }
}