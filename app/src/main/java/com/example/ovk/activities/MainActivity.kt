package com.example.ovk.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ovk.R
import com.example.ovk.adapters.ObceAdapter
import com.example.ovk.parsing.ObceParsingHelper
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var jmenoObce : String
    lateinit var urlAdresa: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jmenoObce = intent.getStringExtra("OBEC_NAME") ?: ""
        urlAdresa = intent.getStringExtra("OBEC_URL_ADRESA") ?: ""


/*
        val url = urlEditText.text.toString()
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)
                val gson = GsonBuilder().create()
                val obcee = gson.fromJson(body, ObceParsingHelper::class.java)
                runOnUiThread {

                }

            }

        })*/



        title = "Obec v Kapse"
        extended_fab_vyhledat.setOnClickListener { view ->
            startActivity(Intent(this, SearchActivity::class.java))
        }

        if (jmenoObce != "" && urlAdresa != "") {
            village_name.text = jmenoObce
            buttons_udalosti_akutality.visibility = View.VISIBLE
        }else{
            buttons_udalosti_akutality.visibility = View.INVISIBLE
        }

        udalosti_button.setOnClickListener {
            val intent = Intent(this, EventsActivity::class.java)
            intent.putExtra("OBEC_URL_ADRESA", urlAdresa)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about -> startActivity(Intent(this, AboutActivity::class.java))
            R.id.settings -> startActivity(Intent(this, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}