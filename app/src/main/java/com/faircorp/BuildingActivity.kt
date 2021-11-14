package com.faircorp

import android.os.Bundle
import android.widget.TextView

const val Building_ID_PARAM = "com.faircorp.buildingId.attribute"

class BuildingActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(Building_ID_PARAM, 0)
        findViewById<TextView>(R.id.txt_building_id).text=id.toString()

    }

}