package com.buzzvil.onboarding

import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("campaigns")
    val campaigns: List<Campaign>
)
