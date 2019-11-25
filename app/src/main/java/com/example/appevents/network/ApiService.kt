package com.example.appevents.network


import com.example.appevents.commun.*
import com.example.appevents.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService{

    @FormUrlEncoded
    @POST(OAUTH2_TOKEN_SUFFIX)
    fun getToken(
        @Field("client_id") clientId:String,
        @Field("client_secret") clientSecret:String,
        @Field("grant_type") grantType:String
    ):Call<TokenResponseModel>

    @GET(SEARCH_EVENT_SUFFIX)
    fun getEventsList(
        @Header("Authorization") authorization:String,
        @Header("x-api-key") apiKey:String
    ):Call<List<EventoDto>>

    @GET(SEARCH_TYPE_SUFFIX)
    fun getTypeList(
        @Header("Authorization") authorization:String,
        @Header("x-api-key") apiKey:String
    ):Call<List<TipoEventoDto>>

    companion object{
        val instanceAuth: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL_AUTH)
                .build()

            retrofit.create(ApiService::class.java)
        }
        val instanceGet: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
}