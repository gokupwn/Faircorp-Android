package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.OnBuildingSelectedListener
import com.faircorp.R

class BuildingAdapter(private val listener: OnBuildingSelectedListener) : RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    inner class BuildingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_building_name)
    }

    private val items = mutableListOf<BuildingDto>()

    fun update(buildings: List<BuildingDto>) {
        items.clear()
        items.addAll(buildings)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_buildings_item, parent, false)
        return BuildingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val building = items[position]
        holder.apply {
            name.text = building.id.toString()
            itemView.setOnClickListener { listener.onBuildingSelected(building.id) }
        }
    }

    override fun onViewRecycled(holder: BuildingAdapter.BuildingViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }
    }
}