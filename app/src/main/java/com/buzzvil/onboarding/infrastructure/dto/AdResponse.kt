package com.buzzvil.onboarding.infrastructure.dto

import com.google.gson.annotations.SerializedName

data class AdResponse(
    @SerializedName("campaigns")
    val campaigns: List<CampaignResponse>
)