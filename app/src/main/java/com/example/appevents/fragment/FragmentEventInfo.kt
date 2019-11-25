package com.example.appevents.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appevents.activity.MainActivity
import com.example.appevents.R
import kotlinx.android.synthetic.main.fragment_event_info.*


class FragmentEventInfo:Fragment() {
    private var eventIdOnList = 0
    fun setEventIdOnList(id: Int) {
        eventIdOnList = id
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_event_info, container, false)
        val titulo = view.findViewById<TextView>(R.id.txtEventoTitulo)
        val descricao = view.findViewById<TextView>(R.id.txtEventoDescricao)
        val inicio = view.findViewById<TextView>(R.id.txtEventoInicio)
        val fim = view.findViewById<TextView>(R.id.txtEventoFim)
        val local = view.findViewById<TextView>(R.id.txtEventoLocal)
        val carga = view.findViewById<TextView>(R.id.txtEventoCarga)
        val qtdvagas = view.findViewById<TextView>(R.id.txtEventoQtdVagas)

        val favbutton = view.findViewById<Button>(R.id.btnFav)
        val activity = activity as MainActivity?
        val evento = activity!!.eventos!![eventIdOnList]
        //Favorite Button

        favbutton.setOnClickListener {
           if (evento.favorite == 1){
               evento.favorite = 0
           }else{
               evento.favorite = 1
           }
        }
        titulo.text = evento!!.titulo
        descricao.text = evento.descricao
        inicio.text = evento.inicioEvento.toString()
        fim.text = evento.fimEvento.toString()
        local.text = evento.localizacao
        carga.text = evento.cargaHoraria.toString()
        qtdvagas.text = evento.qtdVagas.toString()

        return view
    }
}