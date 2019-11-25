package com.example.appevents.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TipoEventoDto(
    var descricao:String,
    @SerializedName("id-tipo-evento") var idTipo: Int
):Parcelable {


}