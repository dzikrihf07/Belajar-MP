package com.dzikri.tugasmobileprograming

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_dashboard.view.*
import kotlinx.android.synthetic.main.dashboard_item.view.*

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateTime = itemView.day
    private val description = itemView.desc
    private val tempNow = itemView.temp
    private val tempMinMax = itemView.temp_range
    private val imageIcon = itemView.imageDescRecycler
    private val clock = itemView.clock

    fun bindItem(item: ListItem) {
        dateTime.text = item.dt?.let { Util.getDateNameRecycler(it) }
        clock.text = item.dt?.let { Util.getClock(it) }

        val str = item.weather?.get(0)?.description
        val strArray = str?.split(" ".toRegex())?.toTypedArray()
        val builder = StringBuilder()
        if (strArray != null) {
            for (s in strArray) {
                val cap = s.substring(0, 1).toUpperCase() + s.substring(1)
                builder.append("$cap ")
            }
        }
        description.setText(builder.toString())

        tempNow.text = item.main?.temp?.let { Util.setFormatTemperature(it) }
        tempMinMax.text = item?.main?.tempMin?.let { Util.setFormatTemperature(it) } + " - " + item.main?.tempMax?.let {
            Util.setFormatTemperature(
                it
            )
        }
        item.weather?.get(0)?.id?.let {
            Util.getArtResourceForWeatherCondition(
                it
            )
        }?.let { imageIcon.setImageResource(it) }

    }
}