package com.buzzvil.onboarding.infrastructure.network

import com.buzzvil.onboarding.domain.model.Ad
import io.reactivex.Single

class BuzzAdClient(
    private val buzzAdApi: BuzzAdApi
) {
    fun get(): Single<Ad> {
        return buzzAdApi.get()
    }
}
