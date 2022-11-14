package com.buzzvil.onboarding

import android.app.Application

class App: Application() {
    internal lateinit var container: Container
    override fun onCreate() {
        super.onCreate()
        container = Container()
    }
}
