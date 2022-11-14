package com.buzzvil.onboarding.domain.model

data class Campaign(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val firstDisplayPriority: Int,
    val firstDisplayWeight: Int,
    val frequency: Int,
    val landingUrl: String,
)
