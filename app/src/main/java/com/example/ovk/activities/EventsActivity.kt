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
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_events.*
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
                    udalostiRecyclerView.adapter = EventsAdapter(seznamUdalosti)
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
}

