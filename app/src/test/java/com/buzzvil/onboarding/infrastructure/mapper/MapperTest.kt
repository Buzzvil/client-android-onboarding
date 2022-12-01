package com.buzzvil.onboarding.infrastructure.mapper

import com.buzzvil.onboarding.MockData
import org.junit.Before
import org.junit.Test

class MapperTest {
    private lateinit var mapper: Mapper

    @Before
    fun setup() {
        mapper = Mapper()
    }

    @Test
    fun ofTest() {
        val adResponse = MockData.adResponse
        val ad = mapper.of(adResponse)

        assert(ad.campaigns[0].id == adResponse.campaigns[0].id)
        assert(ad.campaigns[0].name == adResponse.campaigns[0].name)
        assert(ad.campaigns[0].imageUrl == adResponse.campaigns[0].imageUrl)
        assert(ad.campaigns[0].landingUrl == adResponse.campaigns[0].landingUrl)
        assert(ad.campaigns[0].firstDisplayWeight == adResponse.campaigns[0].firstDisplayWeight)
        assert(ad.campaigns[0].firstDisplayPriority == adResponse.campaigns[0].firstDisplayPriority)
        assert(ad.campaigns[0].frequency == adResponse.campaigns[0].frequency)
    }
}
