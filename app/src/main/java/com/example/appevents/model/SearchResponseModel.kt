package com.example.appevents.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponseModel(val responseEvent: List<EventoDto>):Parcelable