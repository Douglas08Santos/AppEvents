package com.example.appevents.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class EventoDto(
    @SerializedName("carga-horaria") val cargaHoraria:Int,
    val descricao:String,
    @SerializedName("fim-evento") val fimEvento:Int,
    @SerializedName("id-tipo-evento") val idTipoEvento:Int,
    @SerializedName("inicio-evento") val inicioEvento:Int,
    @SerializedName("lat-localizacao") val latLocalizacao:Int,
    @SerializedName("lng-localizacao") val lngLocalizacao:Int,
    @SerializedName("localizacao") val localizacao:String,
    @SerializedName("quantidade-vagas") val qtdVagas:Int,
    val titulo:String

):Parcelable, BaseListModel {

    override val type: Int
        get() = BaseListModel.TYPE_TEXT

    @IgnoredOnParcel
    @SerializedName("id-evento") var id: Long = 0
    var favorite:Int = 0
}