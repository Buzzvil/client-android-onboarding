package com.buzzvil.onboarding

import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BuzzAdClient {
    private val buzzAdApi: BuzzAdApi

    init {
        val gson = Gson().newBuilder()
            .setLenient()
            .create()

        val gsonConverterFactory = GsonConverterFactory.create(gson)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        buzzAdApi = retrofit.create(BuzzAdApi::class.java)
    }

    fun get(): Single<Ad> {
        return buzzAdApi.get()
    }
}
