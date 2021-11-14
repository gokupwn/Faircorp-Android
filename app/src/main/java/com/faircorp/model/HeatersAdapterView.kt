package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.OnHeaterSelectedListener
import com.faircorp.R

class HeaterAdapter(private val listener: OnHeaterSelectedListener) : RecyclerView.Adapter<HeaterAdapter.HeaterViewHolder>() {

    inner class HeaterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_heater_name)
        val room: TextView = view.findViewById(R.id.txt_heater_room)
        val status: TextView = view.findViewById(R.id.txt_heater_status)
    }

    private val items = mutableListOf<HeaterDto>()

    fun update(heaters: List<HeaterDto>) {
        items.clear()
        items.addAll(heaters)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_heaters_item, parent, false)
        return HeaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaterViewHolder, position: Int) {
        val heater = items[position]
        holder.apply {
            name.text = heater.name
            status.text = heater.heaterStatus.toString()
            room.text = heater.roomName
            itemView.setOnClickListener { listener.onHeaterSelected(heater.id)}
        }
    }

    override fun onViewRecycled(holder: HeaterAdapter.HeaterViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }
    }
}