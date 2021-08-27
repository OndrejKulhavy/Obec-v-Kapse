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
import kotlinx.android.synthetic.main.item_udalost.view.*
import java.io.IOException

class EventsAdapter(var seznamUdalosti : EventsParsingHelper) : RecyclerView.Adapter<EventsAdapter.EventsAdapterHolder>() {
    inner class EventsAdapterHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EventViewActivity::class.java)
                intent.putExtra("EVENTS_TITLE", seznamUdalosti.udalosti[adapterPosition].nazev.cs)
                intent.putExtra("EVENTS_DESCRIPTION", seznamUdalosti.udalosti[adapterPosition].dlouhy_popis.cs)
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
            try { nazevUdalostiTextView.text = seznamUdalosti.udalosti[position].nazev.cs }
            catch (e : NullPointerException){}

            try { popisUdalostiTextView.text = seznamUdalosti.udalosti[position].popis.cs }
            catch (e : NullPointerException){popisUdalostiTextView.text = ""}

            if (popisUdalostiTextView.text == ""){
                try { popisUdalostiTextView.text = seznamUdalosti.udalosti[position].dlouhy_popis.cs }
                catch (e : NullPointerException){}

                val popisUdalosti = popisUdalostiTextView.text

                if (popisUdalosti != "" && popisUdalosti.length > 50){
                    popisUdalostiTextView.text = popisUdalosti.slice(IntRange(0,147)).toString() + "..."

                }
            }

        }
    }


    override fun getItemCount(): Int {
        return seznamUdalosti.udalosti.size
    }


}