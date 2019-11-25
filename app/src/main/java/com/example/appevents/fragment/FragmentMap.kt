package com.example.appevents.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appevents.activity.MainActivity
import com.example.appevents.R
import com.example.appevents.model.EventoDto
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class FragmentMap : Fragment(), OnMapReadyCallback, OnMarkerClickListener, OnInfoWindowClickListener {
    private var mMap: GoogleMap? = null
    var eventos: List<EventoDto?>? = null
    private var markerToZoom = -1
    fun setMarkerToZoom(marker: Int) {
        this.markerToZoom = marker
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment = this.childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return v
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        eventos = (activity as MainActivity?)!!.eventos
        val infoWindowAdapter = InfoWindowData(context, activity as MainActivity?)

        mMap!!.setInfoWindowAdapter(infoWindowAdapter)
        mMap!!.setOnMarkerClickListener(this)
        mMap!!.setOnInfoWindowClickListener(this)
        eventsMarkersGenerator()
        if (markerToZoom != -1) zoomToMarker()
    }

    fun eventsMarkersGenerator() {
        var i = 0
        for (evento in eventos!!) {
            if (evento!!.latLocalizacao.toString() != "") {
                val marker = mMap!!.addMarker(MarkerOptions()
                    .position(LatLng(evento.latLocalizacao.toDouble(),
                        evento.lngLocalizacao.toDouble())))
                marker.tag = i
            }
            i++
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        marker.showInfoWindow()
        return false
    }

    override fun onInfoWindowClick(marker: Marker) {
        Toast.makeText(context, "Info window clicked",
            Toast.LENGTH_SHORT).show()
        val position = marker.tag as Int
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        val fragment = FragmentEventInfo()
        fragment.setEventIdOnList(position)
        transaction.replace(R.id.fragmentContainer, fragment as Fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun zoomToMarker() {
        val event = eventos!![markerToZoom]
        val coord = LatLng(event!!.latLocalizacao.toDouble(), event.lngLocalizacao.toDouble())
        val cameraPosition = CameraPosition.Builder()
            .target(coord)
            .zoom(17f).build()
        //Zoom in and animate the camera.
        mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}