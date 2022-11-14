package com.buzzvil.onboarding

import io.reactivex.Single

class BuzzAdClient(
    private val buzzAdApi: BuzzAdApi
) {
    fun get(): Single<Ad> {
        return buzzAdApi.get()
    }
}
