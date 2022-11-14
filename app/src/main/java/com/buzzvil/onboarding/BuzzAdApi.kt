package com.buzzvil.onboarding

import io.reactivex.Single
import retrofit2.http.GET

interface BuzzAdApi {
    @GET("test_ads.json")
    fun get(): Single<Ad>
}
