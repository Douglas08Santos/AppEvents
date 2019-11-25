package com.example.appevents.network


import com.example.appevents.commun.*
import com.example.appevents.model.SearchResponseModel
import com.example.appevents.model.TokenResponseModel
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
        @Header("content-type") contentType: String,
        @Header("authorization") authorization:String,
        @Header("x-api-key") apiKey:String
    ):Call<SearchResponseModel>

    companion object{
        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
}