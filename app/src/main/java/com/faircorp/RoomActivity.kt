package com.faircorp

import android.os.Bundle
import android.widget.TextView

const val ROOM_NAME_PARAM = "com.faircorp.roomName.attribute"

class RoomActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(ROOM_NAME_PARAM)

        findViewById<TextView>(R.id.txt_Name).text = id
    }
}