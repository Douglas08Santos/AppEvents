package com.example.appevents.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class EventoDto(
    @SerializedName("id-evento") var id: Int,
    val titulo:String,
    val descricao:String,
    @SerializedName("inicio-evento") val inicioEvento:Int,
    @SerializedName("fim-evento") val fimEvento:Int,
    @SerializedName("localizacao") val localizacao:String,
    @SerializedName("lat-localizacao") val latLocalizacao:Int,
    @SerializedName("lng-localizacao") val lngLocalizacao:Int,
    @SerializedName("carga-horaria") val cargaHoraria:Int,
    @SerializedName("quantidade-vagas") val qtdVagas:Int,
    @SerializedName("id-tipo-evento") val idTipoEvento:Int

):Parcelable{


    var favorite:Int = 0
}