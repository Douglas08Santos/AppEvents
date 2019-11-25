package com.example.appevents.fragment

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.appevents.activity.MainActivity
import com.example.appevents.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class InfoWindowData(
    private val context: Context?,
    activity: MainActivity?
) : GoogleMap.InfoWindowAdapter {
    private val activity: MainActivity?
    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        val info_window_layout = (context as Activity?)!!.layoutInflater.inflate(R.layout.info_window, null)
        val t = info_window_layout.findViewById<TextView>(R.id.infoWindow_name)
        val position = marker.tag as Int
        val ev = activity!!.eventos!![position]
        t.text = ev!!.titulo
        return info_window_layout
    }

    init {
        this.activity = activity as MainActivity?
    }


}
