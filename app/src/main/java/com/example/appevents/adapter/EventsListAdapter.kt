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
import com.example.appevents.model.EventoDto

class EventsListAdapter(
    private val eventos:List<EventoDto>
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

                    }
                }
            }
        }
        init {
            txtTitulo = itemView.findViewById<View>(R.id.txtItemTitulo) as TextView
            val btnDescricao = itemView.findViewById<View>(R.id.btnItemDescricao) as Button
            btnDescricao.setOnClickListener(this)
            itemView.setOnClickListener(this)
        }
    }



}