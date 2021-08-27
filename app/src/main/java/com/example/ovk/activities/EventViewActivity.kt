package com.example.ovk.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ovk.R
import kotlinx.android.synthetic.main.activity_event_view.*


class EventViewActivity() : AppCompatActivity() {
    lateinit var nazev : String
    lateinit var popis : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_view)
        nazev = intent.getStringExtra("EVENTS_TITLE") ?:""
        popis = intent.getStringExtra("EVENTS_DESCRIPTION") ?:""
        nazevEventTextView.text = nazev
        popisEventTextView.text = popis
        title = ""

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event_view, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.openLink -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}