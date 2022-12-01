package com.buzzvil.onboarding.infrastructure.mapper

import com.buzzvil.onboarding.domain.model.Ad
import com.buzzvil.onboarding.domain.model.Campaign
import com.buzzvil.onboarding.infrastructure.dto.AdResponse
import com.buzzvil.onboarding.infrastructure.dto.CampaignResponse
import javax.inject.Inject

class Mapper @Inject constructor() {
    fun of(adResponse: AdResponse): Ad {
        return Ad(
            campaigns = of(adResponse.campaigns)
        )
    }

    private fun of(campaignsResponse: List<CampaignResponse>): List<Campaign> {
        return campaignsResponse.map {
            Campaign(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                firstDisplayPriority = it.firstDisplayPriority,
                firstDisplayWeight = it.firstDisplayWeight,
                frequency = it.frequency,
                landingUrl = it.landingUrl
            )
        }
    }
}
