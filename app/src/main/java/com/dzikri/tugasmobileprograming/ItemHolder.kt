package com.dzikri.tugasmobileprograming

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashboard_item.view.*

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateTime = itemView.day
    private val description = itemView.desc
    private val tempNow = itemView.temp
    private val tempMinMax = itemView.temp_range

    fun bindItem(item: ListItem) {
        dateTime.text = item.dt.toString()
        description.text = item.weather?.get(0)?.description
        tempNow.text = item.main?.temp.toString()
        tempMinMax.text = item?.main?.tempMin.toString() + " - " + item.main?.tempMax.toString()
    }
}