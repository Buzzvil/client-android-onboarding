package com.buzzvil.onboarding

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Container {
    fun provideBuzzAdClient(): BuzzAdClient {
        val buzzAdApi = provideBuzzAdApi()
        return BuzzAdClient(buzzAdApi)
    }

    private fun provideBuzzAdApi(): BuzzAdApi {
        val retrofit = provideRetrofit()
        return retrofit.create(BuzzAdApi::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        val gson = provideGson()
        val gsonConverterFactory = GsonConverterFactory.create(gson)
        return Retrofit.Builder()
            .baseUrl("https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun provideGson(): Gson {
        return Gson().newBuilder()
            .setLenient()
            .create()
    }
}
