package com.buzzvil.onboarding.infrastructure.repository

import com.buzzvil.onboarding.MockData
import com.buzzvil.onboarding.infrastructure.network.BuzzAdClient
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class AdRepositoryImplTest {
    private lateinit var buzzAdClient: BuzzAdClient
    private lateinit var adRepositoryImpl: AdRepositoryImpl

    @Before
    fun setup() {
        buzzAdClient = mockk(relaxed = true)
        adRepositoryImpl = AdRepositoryImpl(buzzAdClient)
    }

    @Test
    fun testGet() {
        every { buzzAdClient.get() } returns Single.just(MockData.ad)

        adRepositoryImpl.get()
            .test()
            .assertValue(MockData.ad)

        verify(exactly = 1) { buzzAdClient.get() }
    }
}
