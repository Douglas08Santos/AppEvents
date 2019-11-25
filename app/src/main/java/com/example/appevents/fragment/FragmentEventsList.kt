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
import java.util.*

class FragmentEventsList : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_list,
            container, false)

        recyclerView = view.findViewById(R.id.rvEventsList)
        recyclerView?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        val eventos = (Objects.requireNonNull(activity)
                as MainActivity).eventos
        adapter = EventsListAdapter(eventos!!, activity!!.supportFragmentManager)
        recyclerView?.adapter = adapter
        return view
    }


}
