package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.model.WindowDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"

class WindowActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        val window: Call<WindowDto> = ApiServices().windowsApiService.findById(id)



        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        findViewById<TextView>(R.id.txt_window_name).text = it.body()?.name
                        findViewById<TextView>(R.id.txt_room_name).text = it.body()?.roomName
                        findViewById<TextView>(R.id.txt_window_status).text = it.body()?.windowStatus.toString()
                        lifecycleScope.launch(context = Dispatchers.IO) {
                            runCatching { ApiServices().roomApiService.findById(it.body()?.roomId).execute() }
                                .onSuccess {
                                    withContext(context = Dispatchers.Main) {
                                        findViewById<TextView>(R.id.txt_window_current_temperature).text = it.body()?.currentTemperature?.toString()
                                        findViewById<TextView>(R.id.txt_window_target_temperature).text = it.body()?.targetTemperature?.toString()
                                    }
                                }
                                .onFailure {
                                    withContext(context = Dispatchers.Main) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Error on windows loading $it",
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
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }








//        if (window != null) {
//            findViewById<TextView>(R.id.txt_window_name).text = window.name
//            findViewById<TextView>(R.id.txt_room_name).text = window.roomName
////            findViewById<TextView>(R.id.txt_window_current_temperature).text = window.currentTemperature?.toString()
////            findViewById<TextView>(R.id.txt_window_target_temperature).text = window.targetTemperature?.toString()
//            findViewById<TextView>(R.id.txt_window_status).text = window.windowStatus.toString()
//        }
//        }
    }
}