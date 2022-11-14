package com.buzzvil.onboarding

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
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
            .build()
        buzzAdApi = retrofit.create(BuzzAdApi::class.java)
    }

    fun get(): Call<Ad> {
        return buzzAdApi.get()
    }
}
