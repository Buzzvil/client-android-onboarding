package com.buzzvil.onboarding

import android.app.Application
import com.buzzvil.onboarding.di.AppComponent
import com.buzzvil.onboarding.di.DaggerAppComponent

class App: Application() {
    internal lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appContext(applicationContext)
            .build()
    }
}
