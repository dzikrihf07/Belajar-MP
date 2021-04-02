package com.dzikri.tugasmobileprograming

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dashboard.*
import okhttp3.*
import java.io.IOException


class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //run("https://api.openweathermap.org/data/2.5/forecast?q=purwakarta,ID&appid=2ce659b9c25fc6fe3a07de4ca71d1dac")

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.btPwk ->
                    if (checked) {

                        NetworkConfig().getService().getWeathers("purwakarta,ID", "2ce659b9c25fc6fe3a07de4ca71d1dac").enqueue(object : retrofit2.Callback<ResultWeather> {

                            override fun onResponse(
                                call: retrofit2.Call<ResultWeather>,
                                response: retrofit2.Response<ResultWeather>
                            ) {
                                var item = response.body()
                                Log.d("response", response.body().toString())

                                tgl.text = item?.list?.get(0)?.dt?.let { Util.getDateName(it) }
                                tempMax.text =
                                    item?.list?.get(0)?.main?.temp?.let { Util.setFormatTemperature(it) }
                                tempMin.text =
                                    item?.list?.get(0)?.main?.tempMin?.let { Util.setFormatTemperature(it) } + " - " + item?.list?.get(
                                        0
                                    )?.main?.tempMax?.let { Util.setFormatTemperature(it) }

                                val str = item?.list?.get(0)?.weather?.get(0)?.description.toString()
                                val strArray = str.split(" ".toRegex()).toTypedArray()
                                val builder = StringBuilder()
                                for (s in strArray) {
                                    val cap = s.substring(0, 1).toUpperCase() + s.substring(1)
                                    builder.append("$cap ")
                                }
                                textDesc.setText(builder.toString())
                                item?.list?.get(0)?.weather?.get(0)?.id?.let {
                                    Util.getArtResourceForWeatherCondition(
                                        it
                                    )
                                }?.let { imageDesc.setImageResource(it) }

                                var list = item?.list
                                var itemAdp = ItemAdapter(list as List<ListItem>)

                                recycleItem.apply {
                                    layoutManager = LinearLayoutManager(this@DashboardActivity)
                                    adapter = itemAdp
                                }
                            }

                            override fun onFailure(call: retrofit2.Call<ResultWeather>, t: Throwable) {
                                Log.d("error response service", t.message.toString())
                                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG)
                                    .show()
                            }
                        })
                    }
                R.id.btDpk ->
                    if (checked) {

                        NetworkConfig().getService().getWeathers("depok,ID", "2ce659b9c25fc6fe3a07de4ca71d1dac").enqueue(object : retrofit2.Callback<ResultWeather> {

                            override fun onResponse(
                                call: retrofit2.Call<ResultWeather>,
                                response: retrofit2.Response<ResultWeather>
                            ) {
                                var item = response.body()
                                Log.d("response", response.body().toString())

                                tgl.text = item?.list?.get(0)?.dt?.let { Util.getDateName(it) }
                                tempMax.text =
                                    item?.list?.get(0)?.main?.temp?.let { Util.setFormatTemperature(it) }
                                tempMin.text =
                                    item?.list?.get(0)?.main?.tempMin?.let { Util.setFormatTemperature(it) } + " - " + item?.list?.get(
                                        0
                                    )?.main?.tempMax?.let { Util.setFormatTemperature(it) }

                                val str = item?.list?.get(0)?.weather?.get(0)?.description.toString()
                                val strArray = str.split(" ".toRegex()).toTypedArray()
                                val builder = StringBuilder()
                                for (s in strArray) {
                                    val cap = s.substring(0, 1).toUpperCase() + s.substring(1)
                                    builder.append("$cap ")
                                }
                                textDesc.setText(builder.toString())
                                item?.list?.get(0)?.weather?.get(0)?.id?.let {
                                    Util.getArtResourceForWeatherCondition(
                                        it
                                    )
                                }?.let { imageDesc.setImageResource(it) }

                                var list = item?.list
                                var itemAdp = ItemAdapter(list as List<ListItem>)

                                recycleItem.apply {
                                    layoutManager = LinearLayoutManager(this@DashboardActivity)
                                    adapter = itemAdp
                                }
                            }

                            override fun onFailure(call: retrofit2.Call<ResultWeather>, t: Throwable) {
                                Log.d("error response service", t.message.toString())
                                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG)
                                    .show()
                            }
                        })
                    }
            }
        }
    }

    fun run(url: String){
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) =
                println(response.body?.string())
        })
    }
}
