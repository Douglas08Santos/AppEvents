package com.example.appevents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appevents.util.ServiceTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var serviceTask = ServiceTask()
        serviceTask.execute()
    }


}
