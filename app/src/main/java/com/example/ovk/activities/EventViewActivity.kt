package com.example.ovk.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ovk.R
import kotlinx.android.synthetic.main.activity_event_view.*


class EventViewActivity() : AppCompatActivity() {
    lateinit var nazev : String
    lateinit var popis : String
    lateinit var dlouhy_popis : String
    lateinit var iri : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_view)


        nazev = intent.getStringExtra("EVENTS_TITLE") ?:""
        popis = intent.getStringExtra("EVENTS_DESCRIPTION") ?:""
        dlouhy_popis = intent.getStringExtra("EVENTS_LONG_DESCRIPTION") ?:""
        iri = intent.getStringExtra("EVENTS_IRI") ?:""
        val ma_vhodne_pro_deti = intent.getBooleanExtra("EVENTS_has_CHILD",false)
        val ma_vhodne_pro_zvirata = intent.getBooleanExtra("EVENTS_has_ANIMALS",false)
        val vhodne_pro_deti = intent.getBooleanExtra("EVENTS_CHILD",false)
        val vhodne_pro_zvirata = intent.getBooleanExtra("EVENTS_ANIMALS",false)

        title = ""

        if (nazev != "")
            nazevEventTextView.text = nazev
        if (popis != "")
            popisEventTextView.text = popis
        if (dlouhy_popis != "")
            popisEventTextView.text = dlouhy_popis

        if (ma_vhodne_pro_deti && vhodne_pro_deti)
            chip_vhodneProDeti.visibility = View.VISIBLE
        if (ma_vhodne_pro_zvirata && vhodne_pro_zvirata)
            chip_vhodneProZvirata.visibility = View.VISIBLE
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event_view, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.openLink -> {
                if (iri != "")
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(iri)))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

