package com.example.ovk.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ovk.parsing.ObceParsingHelper
import com.example.ovk.R
import com.example.ovk.activities.MainActivity
import kotlinx.android.synthetic.main.item_obec.view.*

class ObceAdapter(var seznamObci : ObceParsingHelper) : RecyclerView.Adapter<ObceAdapter.SearchObceHolder>() {
    inner class SearchObceHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                Log.e("Zpětná vazba",seznamObci.results.bindings[adapterPosition].nazev.value)

                val intent = Intent(itemView.context, MainActivity::class.java)
                intent.putExtra("OBEC_NAME",seznamObci.results.bindings[adapterPosition].nazev.value)
                intent.putExtra("OBEC_URL_ADRESA",seznamObci.results.bindings[adapterPosition].url.value)
                startActivity(itemView.context,intent,null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchObceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_obec,parent,false)
        return SearchObceHolder(view)
    }

    override fun onBindViewHolder(holder: SearchObceHolder, position: Int) {
        holder.itemView.apply {
            item_obec_Title.text = seznamObci.results.bindings[position].nazev.value


            // TODO:  Zobrazení zda je obec podporována či není
/*
            if (seznamObci[position].isSupported)
                item_obec_Podporuje.visibility = View.VISIBLE
            else
                item_obec_Nepodporuje.visibility = View.VISIBLE
*/
        }
    }

    override fun getItemCount(): Int {
        return seznamObci.results.bindings.size
    }
}