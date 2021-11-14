package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.apiservices.ApiServices
import com.faircorp.model.BuildingRoomsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val Building_ID_PARAM = "com.faircorp.buildingId.attribute"

class BuildingActivity : BasicActivity(), OnRoomBuildingSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(Building_ID_PARAM, 0)

        val recyclerView = findViewById<RecyclerView>(R.id.list_building_rooms)
        val adapter = BuildingRoomsAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomApiService.findRoomsByBuildingId(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        adapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on building rooms loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }

    override fun onRoomBuildingSelected(id: Long) {
        val intent = Intent(this, RoomActivity::class.java).putExtra(ROOM_NAME_PARAM, id)
        startActivity(intent)
    }

}