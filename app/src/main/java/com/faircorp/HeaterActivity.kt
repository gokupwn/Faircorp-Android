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

const val HEATER_NAME_PARAM = "com.faircorp.heatername.attribute"

class HeaterActivity : BasicActivity() {

    var id: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getLongExtra(HEATER_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().heaterApiService.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        findViewById<TextView>(R.id.txt_heater_name).text = it.body()?.name
                        findViewById<TextView>(R.id.txt_room_name).text = it.body()?.roomName
                        findViewById<TextView>(R.id.txt_heater_status).text =
                            it.body()?.heaterStatus.toString()
                        findViewById<TextView>(R.id.txt_heater_power).text = it.body()?.power.toString()
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on Heater loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
    }

    fun switchStatus(view: View) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().heaterApiService.switchStatus(id).execute() }
        }
    }

}