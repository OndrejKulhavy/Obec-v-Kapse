package com.example.ovk.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ovk.R
import com.example.ovk.activities.MainActivity
import com.example.ovk.parsing.ObceParsingHelper
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.item_obec.view.*
import okhttp3.*
import java.io.IOException


class ObceAdapter(var seznamObci: ObceParsingHelper) : RecyclerView.Adapter<ObceAdapter.SearchObceHolder>() {
    inner class SearchObceHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                Log.e("Zpětná vazba", seznamObci.results.bindings[adapterPosition].nazev.value)

                val intent = Intent(itemView.context, MainActivity::class.java)
                intent.putExtra("OBEC_NAME", itemView.item_obec_Title.text)
                intent.putExtra("OBEC_URL_ADRESA", seznamObci.results.bindings[adapterPosition].url.value)
                startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchObceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_obec, parent, false)
        return SearchObceHolder(view)
    }

    override fun onBindViewHolder(holder: SearchObceHolder, position: Int) {
        holder.itemView.apply {
            var jmenoObce = seznamObci.results.bindings[position].misto.value
            jmenoObce = jmenoObce.replace("[^0-9]".toRegex(), "")


            val url : String= "https://linked.cuzk.cz.opendata.cz/sparql?default-graph-uri=&query=select%20?nazev%20where%20{%3Chttps://linked.cuzk.cz/resource/ruian/obec/" + jmenoObce + "%3E%20schema:name%20?nazev.%20FILTER%20(LANG(?nazev)=%22cs%22)}%20&&timeout=0&format=application/json"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            var nazev = ""
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    var body = response?.body?.string()
                    println(body)

                    val gson = GsonBuilder().create()
                    val nazevObceParsing = gson.fromJson(body, ObceParsingHelper::class.java)
                    nazev = nazevObceParsing.results.bindings[0].nazev.value
                    handler.post { item_obec_Title.text = nazev }
                }

            })




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