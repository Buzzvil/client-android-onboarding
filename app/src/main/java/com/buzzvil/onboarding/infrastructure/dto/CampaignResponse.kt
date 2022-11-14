package com.buzzvil.onboarding.infrastructure.dto

import com.google.gson.annotations.SerializedName

data class CampaignResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("firstDisplayPriority")
    val firstDisplayPriority: Int,
    @SerializedName("firstDisplayWeight")
    val firstDisplayWeight: Int,
    @SerializedName("frequency")
    val frequency: Int,
    @SerializedName("landing_url")
    val landing_url: String,
)
