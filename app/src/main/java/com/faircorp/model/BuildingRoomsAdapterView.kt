package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.OnRoomBuildingSelectedListener

import com.faircorp.R

class BuildingRoomsAdapter(private val listener: OnRoomBuildingSelectedListener) : RecyclerView.Adapter<BuildingRoomsAdapter.BuildingRoomsViewHolder>() {

    inner class BuildingRoomsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_building_room_name)
    }

    private val items = mutableListOf<RoomDto>()

    fun update(rooms: List<RoomDto>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingRoomsAdapter.BuildingRoomsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_building_rooms_item, parent, false)
        return BuildingRoomsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingRoomsAdapter.BuildingRoomsViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            name.text = room.name
            itemView.setOnClickListener { listener.onRoomBuildingSelected(room.id) }
        }
    }

    override fun onViewRecycled(holder: BuildingRoomsAdapter.BuildingRoomsViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}