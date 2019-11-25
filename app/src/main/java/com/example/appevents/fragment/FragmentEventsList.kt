package com.example.appevents.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appevents.activity.MainActivity
import com.example.appevents.R
import com.example.appevents.adapter.EventsListAdapter
import com.example.appevents.model.EventoDto
import com.example.appevents.repository.SQLiteRepository
import java.util.*

class FragmentEventsList : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: EventsListAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    var eventos: MutableList<EventoDto>? = null
    private var eventoRepository: SQLiteRepository? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_list,
            container, false)

        val eventos = (Objects.requireNonNull(activity)
                as MainActivity).eventos

        val eventoRepository = context?.let { SQLiteRepository(it) }
        adapter = EventsListAdapter(eventos!!, activity!!.supportFragmentManager)
        recyclerView?.adapter = adapter

        initRecyclerView()
        updateList()
        return view
    }

    //Iniciar RecyclerView
    fun initRecyclerView() {
        recyclerView = view?.findViewById(R.id.rvEventsList)
        recyclerView?.adapter = adapter
        val layoutMAnager = LinearLayoutManager(activity?.applicationContext)
        recyclerView?.layoutManager = layoutMAnager
    }
    private fun list(eventos:MutableList<EventoDto>){
        this.eventos  = eventos

        adapter = EventsListAdapter(eventos!!,
            activity!!.supportFragmentManager)

        recyclerView?.adapter = adapter
    }

    fun updateList(){
        eventoRepository?.list { list(it) }
    }



}
