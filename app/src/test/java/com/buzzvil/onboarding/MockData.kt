package com.buzzvil.onboarding

import com.buzzvil.onboarding.domain.model.Ad
import com.buzzvil.onboarding.domain.model.Campaign
import com.buzzvil.onboarding.infrastructure.dto.AdResponse
import com.buzzvil.onboarding.infrastructure.dto.CampaignResponse

object MockData {
    val campaignResponse = CampaignResponse(
        id = 1,
        name = "campaign",
        imageUrl = "https://buzzvil.com/image",
        firstDisplayPriority = 1,
        firstDisplayWeight = 100,
        frequency = 10,
        landingUrl = "https://buzzvil.com/landing"
    )

    val campaignsResponse = listOf(campaignResponse)

    val adResponse = AdResponse(campaignsResponse)

    val campaign = Campaign(
        id = 1,
        name = "campaign",
        imageUrl = "https://buzzvil.com/image",
        firstDisplayPriority = 1,
        firstDisplayWeight = 100,
        frequency = 10,
        landingUrl = "https://buzzvil.com/landing"
    )

    val campaigns = listOf(campaign)

    val ad = Ad(campaigns)
}