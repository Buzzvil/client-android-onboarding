package com.buzzvil.onboarding.di

import android.content.Context
import com.buzzvil.onboarding.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }
}
