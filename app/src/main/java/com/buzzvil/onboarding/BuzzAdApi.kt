package com.buzzvil.onboarding

import retrofit2.Call
import retrofit2.http.GET

interface BuzzAdApi {
    @GET("test_ads.json")
    fun get(): Call<Ad>
}
