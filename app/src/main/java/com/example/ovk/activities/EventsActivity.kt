package com.example.ovk.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ovk.adapters.EventsAdapter
import com.example.ovk.parsing.EventsParsingHelper
import com.example.ovk.R
import com.example.ovk.dataclasses.Obsahuje
import com.example.ovk.dataclasses.ParsedEventsSimplifed
import com.example.ovk.dataclasses.UlozenaData
import com.example.ovk.dataclasses.ZjednodusenaUdalost
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.item_udalost.view.*
import okhttp3.*
import java.io.IOException

class EventsActivity : AppCompatActivity() {
    lateinit var urlAdresa: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        title = "Události"
        urlAdresa = intent.getStringExtra("OBEC_URL_ADRESA") ?: ""


        val request = Request.Builder().url(urlAdresa).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                var body = "{\"udalosti\":"+ response?.body?.string() + "}"
                println(body)

                val gson = GsonBuilder().create()
                val seznamUdalosti = gson.fromJson(body, EventsParsingHelper::class.java)
                runOnUiThread {
                    var zjednodusen_seznamUdalosti = simplifyList(seznamUdalosti)
                    //println(zjednodusen_seznamUdalosti.list[0].obsahuje.maIRI)
                    udalostiRecyclerView.adapter = EventsAdapter(zjednodusen_seznamUdalosti)
                    udalostiRecyclerView.layoutManager = LinearLayoutManager(this@EventsActivity)
                    progressBar2.visibility = View.INVISIBLE
                }

            }

        })

        extended_fab_filtr.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.filter_dialog,null)
            val filtrDialog = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setIcon(R.drawable.ic_outline_sort_24)
                    .setTitle("Filtr")
            val filtrDialogShown = filtrDialog.show()
        }


    }

    private fun simplifyList(seznamUdalosti : EventsParsingHelper) : ParsedEventsSimplifed{
        val listZjednodusenychUdalsti : ArrayList<ZjednodusenaUdalost> = ArrayList()
        //listZjednodusenychUdalsti.add(ZjednodusenaUdalost(Obsahuje(true,false,true,true,false,true,false), UlozenaData("","","",true,true)))

        for (item in seznamUdalosti.udalosti){
            var iri = ""
            var nazev = ""
            var popis = ""
            var dlouhy_popis = ""
            var vhodne_pro_deti = false
            var vhodne_pro_zvirata = false

            var ma_iri = false
            var ma_nazev = false
            var ma_popis = false
            var ma_dlouhy_popis = false
            var ma_vhodne_pro_deti = false
            var ma_vhodne_pro_zvirata = false

            try {
                iri = item.iri
                ma_iri = true
            }
            catch (e : NullPointerException){
                println("ou shitt mas stesti")
            }

            try {
                nazev = item.nazev.cs
                ma_nazev = true
            }
            catch (e : NullPointerException){
                println("ou shitt mas stesti")
            }

            try {
                popis = item.popis.cs
                ma_popis = true
            }
            catch (e : NullPointerException){
                println("ou shitt mas stesti")
            }

            try {
                dlouhy_popis = item.dlouhy_popis.cs
                ma_dlouhy_popis = true
            }
            catch (e : NullPointerException){
                println("ou shitt mas stesti")
            }

            try {
                vhodne_pro_deti = item.vhodne_pro_deti
                ma_vhodne_pro_deti = true
            }
            catch (e : NullPointerException){
                println("ou shitt mas stesti")
            }

            try {
                vhodne_pro_zvirata = item.vhodne_pro_zvirata
                ma_vhodne_pro_zvirata = true
            }
            catch (e : NullPointerException){
                println("ou shitt mas stesti")
            }

            listZjednodusenychUdalsti.add(ZjednodusenaUdalost(
                    Obsahuje(ma_iri,ma_nazev,ma_dlouhy_popis,ma_popis,ma_vhodne_pro_deti,ma_vhodne_pro_zvirata),
                    UlozenaData(iri,nazev,popis,dlouhy_popis,vhodne_pro_deti,vhodne_pro_zvirata)
            ))

        }
        return ParsedEventsSimplifed(listZjednodusenychUdalsti)
    }
}

