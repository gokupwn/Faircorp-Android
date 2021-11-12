package com.faircorp.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.OnRoomSelectedListener
import com.faircorp.R

// ┌────────────────┐        ┌────────────────┐         ┌────────────────┐
// │                │        │                │         │                │
// │Rooms Activity  ├────────┤  Rooms Adapter ├─────────┤Room Activity   │
// │                │        │                │         │                │
// │                │        │      View      │         │                │
// └────────────────┘        └────────────────┘         └────────────────┘
//
// ┌────────────────┐                                   ┌───────────────┐
// │                │                                   │Window 1 in R1 │
// │   Room 1  (R1) ├──────────────────────────────────►│               │
// ├────────────────┤                                   ├───────────────┤
// │                │                                   │               │
// │   Room 2 (R2)  │                                   │Window 2 in R2 │
// ├────────────────┤                                   └───────────────┘
// │                │
// │                │
// └────────────────┘

class RoomAdapter(private val listener: OnRoomSelectedListener): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_roomName)
        val targetTemperature: TextView = view.findViewById(R.id.txt_target_temperature)
        val currentTemperature: TextView = view.findViewById(R.id.txt_current_temperature)
    }

    private val items = mutableListOf<RoomDto>()

    fun update(rooms: List<RoomDto>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            name.text = room.name
            targetTemperature.text = "target: " + room.targetTemperature.toString()
            currentTemperature.text = "current: " + room.currentTemperature.toString()
            itemView.setOnClickListener { listener.onRoomSelected(room.id) }
        }
    }

    override fun onViewRecycled(holder: RoomViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }


}
