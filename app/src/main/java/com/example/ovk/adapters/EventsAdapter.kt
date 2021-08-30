package com.example.ovk.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ovk.parsing.EventsParsingHelper
import com.example.ovk.R
import com.example.ovk.activities.EventViewActivity
import com.example.ovk.activities.MainActivity
import com.example.ovk.dataclasses.ParsedEventsSimplifed
import kotlinx.android.synthetic.main.item_udalost.view.*
import java.io.IOException

class EventsAdapter(var seznamUdalosti : ParsedEventsSimplifed) : RecyclerView.Adapter<EventsAdapter.EventsAdapterHolder>() {
    inner class EventsAdapterHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EventViewActivity::class.java)

                intent.putExtra("EVENTS_has_ANIMALS", seznamUdalosti.list[adapterPosition].obsahuje.maVHODNE_PRO_ZVIRITA)
                intent.putExtra("EVENTS_has_CHILD", seznamUdalosti.list[adapterPosition].obsahuje.maVHODNE_PRO_DETI)

                intent.putExtra("EVENTS_LONG_DESCRIPTION", seznamUdalosti.list[adapterPosition].data.dlouhy_popis)
                intent.putExtra("EVENTS_ANIMALS", seznamUdalosti.list[adapterPosition].data.vhodne_pro_zvriata)
                intent.putExtra("EVENTS_CHILD", seznamUdalosti.list[adapterPosition].data.vhodne_pro_deti)
                intent.putExtra("EVENTS_TITLE", seznamUdalosti.list[adapterPosition].data.nazev)
                intent.putExtra("EVENTS_DESCRIPTION", seznamUdalosti.list[adapterPosition].data.popis)
                intent.putExtra("EVENTS_IRI", seznamUdalosti.list[adapterPosition].data.iri)

                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_udalost,parent,false)
        return EventsAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: EventsAdapterHolder, position: Int) {

        holder.itemView.apply {
            nazevUdalostiTextView.text = seznamUdalosti.list[position].data.nazev
            if (seznamUdalosti.list[position].obsahuje.maPOPIS)
                popisUdalostiTextView.text = seznamUdalosti.list[position].data.popis

            if (seznamUdalosti.list[position].obsahuje.maDLOUHY_POPIS && !seznamUdalosti.list[position].obsahuje.maPOPIS){
                val dlouhy_popis = seznamUdalosti.list[position].data.dlouhy_popis
                if (dlouhy_popis.length > 50){
                    popisUdalostiTextView.text = dlouhy_popis.slice(IntRange(0,147)) + "..."

                }
            }


        }
    }


    override fun getItemCount(): Int {
        return seznamUdalosti.list.size
    }


}