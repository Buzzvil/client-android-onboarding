package com.buzzvil.onboarding.infrastructure.network

import com.buzzvil.onboarding.MockData
import com.buzzvil.onboarding.domain.model.Ad
import com.buzzvil.onboarding.infrastructure.dto.AdResponse
import com.buzzvil.onboarding.infrastructure.mapper.Mapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class BuzzAdClientTest {
    private lateinit var buzzAdApi: BuzzAdApi
    private lateinit var mapper: Mapper
    private lateinit var buzzAdClient: BuzzAdClient

    @Before
    fun setup() {
        buzzAdApi = mockk(relaxed = true)
        mapper = mockk(relaxed = true)
        buzzAdClient = BuzzAdClient(buzzAdApi, mapper)
    }

    @Test
    fun testGet() {
        every { buzzAdApi.get() } returns Single.just(MockData.adResponse)
        every { mapper.of(any()) } returns MockData.ad

        buzzAdClient.get()
            .test()
            .assertValue(MockData.ad)

        verify(exactly = 1) { buzzAdApi.get() }
        verify(exactly = 1) { mapper.of(MockData.adResponse) }
    }
}
