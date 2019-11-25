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
        val inicio = view.findViewById<TextView>(R.id.txtEventoInicio)
        val descricao = view.findViewById<TextView>(R.id.txtEventoDescricao)
        val local = view.findViewById<TextView>(R.id.txtEventoLocal)
        val qtdvagas = view.findViewById<TextView>(R.id.txtEventoQtdVagas)
        val carga = view.findViewById<TextView>(R.id.txtEventoCarga)
        val fim = view.findViewById<TextView>(R.id.txtEventoFim)

        val favbutton = view.findViewById<Button>(R.id.btnFav)
        val activity = activity as MainActivity?
        val evento = activity!!.eventos!![eventIdOnList]
        //Favorite Button
        /*
        favbutton.setOnClickListener {
            activity.eventos?.drop(eventIdOnList)
            val transaction = getActivity()!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, FragmentLogi())
            transaction.addToBackStack(null)
            transaction.commit()
        }*/
        txtEventoTitulo.text = evento!!.titulo
        txtEventoInicio.text = evento.inicioEvento.toString()
        txtEventoDescricao.text = evento.descricao
        txtEventoLocal.text = evento.localizacao
        txtEventoQtdVagas.text = evento.qtdVagas.toString()
        txtEventoCarga.text = evento.cargaHoraria.toString()
        txtEventoFim.text = evento.fimEvento.toString()

        return view
    }
}