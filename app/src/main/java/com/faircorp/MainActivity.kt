package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import androidx.cardview.widget.CardView


class MainActivity : BasicActivity(), OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val cardBuildings = findViewById<CardView>(R.id.buildings)
        val cardRooms = findViewById<CardView>(R.id.rooms)
        val cardWindows = findViewById<CardView>(R.id.windows)
        val cardHeaters = findViewById<CardView>(R.id.heaters)

        cardBuildings.setOnClickListener(this)
        cardRooms.setOnClickListener(this)
        cardWindows.setOnClickListener(this)
        cardHeaters.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.buildings -> startActivity(
                Intent(this, BuildingsActivity::class.java)
            )

            R.id.rooms -> startActivity(
                Intent(this, RoomsActivity::class.java)
            )

            R.id.windows -> startActivity(
                Intent(this, WindowsActivity::class.java)
            )

            R.id.heaters -> startActivity(
                Intent(this, HeatersActivity::class.java)
            )
        }

    }
}