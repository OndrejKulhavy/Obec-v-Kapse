package com.example.ovk.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ovk.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var jmenoObce : String
    lateinit var urlAdresa: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jmenoObce = intent.getStringExtra("OBEC_NAME") ?: ""
        urlAdresa = intent.getStringExtra("OBEC_URL_ADRESA") ?: ""

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