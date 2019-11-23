package com.example.appevents.model

import com.google.gson.annotations.SerializedName

data class TokenResponseModel(
    @SerializedName("access_token") val accessToken:String,
    @SerializedName("token_type") val tokenType:String,
    @SerializedName("refresh_token") val refreshToken:String,
    @SerializedName("expires_in") val expiresIn:Long,
    @SerializedName("scope") val scope: String

) {
}