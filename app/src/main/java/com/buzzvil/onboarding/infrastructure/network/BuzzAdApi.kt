package com.buzzvil.onboarding.infrastructure.network

import com.buzzvil.onboarding.infrastructure.dto.AdResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BuzzAdApi {
    @GET("test_ads.json")
    fun get(): Single<AdResponse>
}
