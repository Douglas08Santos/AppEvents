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
    @SerializedName("inicio-evento") val inicioEvento:Long,
    @SerializedName("localizacao") val localizacao:String,
    @SerializedName("lat-localizacao") val latLocalizacao:Float,
    @SerializedName("lng-localizacao") val lngLocalizacao:Float,
    @SerializedName("carga-horaria") val cargaHoraria:Int,
    @SerializedName("quantidade-vagas") val qtdVagas:Int,
    @SerializedName("id-tipo-evento") val idTipoEvento:Int

):Parcelable{


//    @IgnoredOnParcel
//    var favorite:Int = 0
    @IgnoredOnParcel
    var tipo:String = ""
}