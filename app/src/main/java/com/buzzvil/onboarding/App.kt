package com.buzzvil.onboarding

import android.app.Application
import com.buzzvil.onboarding.di.AppComponent

class App: Application() {
    internal lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
    }
}
