package com.example.appevents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appevents.R
import com.example.appevents.fragment.FragmentEventInfo
import com.example.appevents.fragment.FragmentMap
import com.example.appevents.model.EventoDto

class EventsListAdapter(
    private val eventos:List<EventoDto>,
    private val fragmentManager: FragmentManager
): RecyclerView.Adapter<EventsListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       val v = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_event_list, parent, false) as View

        return  VH(v)
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.txtTitulo.text = eventos[position].titulo
    }


    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var txtTitulo: TextView

        override fun onClick(view: View) {
            val position = adapterPosition // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                when (view.id) {
                    R.id.btnItemDescricao -> {
                        val transaction = fragmentManager.beginTransaction()
                        val fragment = FragmentEventInfo()
                        fragment.setEventIdOnList(position)
                        transaction.replace(R.id.fragmentContainer, fragment as Fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    R.id.btnItemMapa -> {
                        if (eventos!![position]!!.latLocalizacao.toString() == "") {
                            //Toast.makeText(this, "Sem Lat",
                              //  Toast.LENGTH_SHORT).show()
                        }
                        val transaction = fragmentManager.beginTransaction()
                        val fragment = FragmentMap()
                        fragment.setMarkerToZoom(position)
                        transaction.replace(R.id.fragmentContainer, fragment as Fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                        fragmentManager.executePendingTransactions()
                    }
                }
            }
        }
        init {
            txtTitulo = itemView.findViewById<View>(R.id.txtItemTitulo) as TextView
            val btnDescricao = itemView.findViewById<View>(R.id.btnItemDescricao) as Button
            btnDescricao.setOnClickListener(this) // calling onClick() method
            val btnMap = itemView.findViewById<View>(R.id.btnItemMapa) as Button
            btnMap.setOnClickListener(this)
            itemView.setOnClickListener(this)
        }
    }



}