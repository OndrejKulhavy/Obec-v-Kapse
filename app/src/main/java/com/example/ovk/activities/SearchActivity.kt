package com.example.ovk.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ovk.parsing.ObceParsingHelper
import com.example.ovk.R
import com.example.ovk.adapters.ObceAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_search.*
import okhttp3.*
import java.io.IOException

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        title = "Výběr obce"
       /*
        adapter.notifyDataSetChanged()
        adapter.notifyItemInserted(seznamObci.size - 1)
        */

        stahnout_button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val url = urlEditText.text.toString()
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response?.body?.string()
                    println(body)
                    val gson = GsonBuilder().create()
                    val obcee = gson.fromJson(body, ObceParsingHelper::class.java)
                    runOnUiThread {
                        val adapter = ObceAdapter(obcee)
                        seznamObciRecyclerView.adapter = adapter
                        seznamObciRecyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
                        //progressBar.visibility = View.INVISIBLE
                        progressBar.visibility = View.INVISIBLE
                    }

                }

            })

        }

    }
}
