package com.dzikri.tugasmobileprograming

import ResultWeather
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import okhttp3.*
import java.io.IOException

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //run("https://api.openweathermap.org/data/2.5/forecast?q=purwakarta,ID&appid=2ce659b9c25fc6fe3a07de4ca71d1dac")

        NetworkConfig().getService().getWeathers("purwakarta,ID", "2ce659b9c25fc6fe3a07de4ca71d1dac").enqueue(object : retrofit2.Callback<ResultWeather> {

            override fun onResponse(call: retrofit2.Call<ResultWeather>, response: retrofit2.Response<ResultWeather>) {
                //var item = response.body()
                Log.d("response", response.body().toString())

                //txtKota.setText(item.city.name)
            }

            override fun onFailure(call: retrofit2.Call<ResultWeather>, t: Throwable) {
                Log.d("error response service", t.message.toString())

                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }

    fun run(url: String){
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body?.string())
        })
    }

}