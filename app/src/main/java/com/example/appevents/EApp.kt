package com.example.appevents

import android.app.Application
import android.content.Context

class EApp: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: EApp? = null

        fun applicationContext(): Context? {
            return instance!!.applicationContext
        }
    }
}