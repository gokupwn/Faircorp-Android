package com.faircorp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.apiservices.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"

class WindowActivity : BasicActivity() {


    var id: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        findViewById<TextView>(R.id.txt_window_name).text = it.body()?.name
                        findViewById<TextView>(R.id.txt_room_name).text = it.body()?.roomName
                        findViewById<TextView>(R.id.txt_heater_status).text =
                            it.body()?.windowStatus.toString()
                        lifecycleScope.launch(context = Dispatchers.IO) {
                            runCatching {
                                ApiServices().roomApiService.findById(it.body()?.roomId).execute()
                            }
                                .onSuccess {
                                    withContext(context = Dispatchers.Main) {
                                        findViewById<TextView>(R.id.txt_heater_power).text =
                                            it.body()?.currentTemperature?.toString()
                                        findViewById<TextView>(R.id.txt_window_target_temperature).text =
                                            it.body()?.targetTemperature?.toString()
                                    }
                                }
                                .onFailure {
                                    withContext(context = Dispatchers.Main) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Error on Temperatures loading $it",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on window loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
    }

    fun switchStatus(view: View) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.switchStatus(id).execute() }
        }
    }
}