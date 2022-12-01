package com.buzzvil.onboarding.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buzzvil.onboarding.MockData
import com.buzzvil.onboarding.domain.repository.AdRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class AdViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: AdRepository
    private lateinit var schedulerProvider: SchedulerProvider
    private lateinit var testScheduler: TestScheduler
    private lateinit var viewModel: AdViewModel


    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        schedulerProvider = mockk(relaxed = true)
        testScheduler = TestScheduler()
        viewModel = AdViewModel(repository, schedulerProvider)
    }

    @Test
    fun testLoad_whenRepositoryReturnsAd() {
        every { repository.get() } returns Single.just(MockData.ad)
        every { schedulerProvider.io() } returns testScheduler
        every { schedulerProvider.main() } returns testScheduler

        val latch = CountDownLatch(2)
        val actual = mutableListOf<AdViewModel.ViewState>()
        viewModel.viewState.observeForever {
            latch.countDown()
            actual.add(it)
        }
        latch.await(3, TimeUnit.SECONDS)
        viewModel.load()
        testScheduler.triggerActions()

        val loadingViewState = AdViewModel.ViewState(isLoaded = false)
        assert(actual[0] == loadingViewState)

        val loadedViewState = AdViewModel.ViewState(ad = MockData.ad, isLoaded = true)
        assert(actual[1] == loadedViewState)

        verify(exactly = 1) { repository.get() }
    }

    @Test
    fun testLoad_whenRepositoryReturnsError() {
        every { repository.get() } returns Single.error(Exception())
        every { schedulerProvider.io() } returns testScheduler
        every { schedulerProvider.main() } returns testScheduler

        val latch = CountDownLatch(2)
        val actual = mutableListOf<AdViewModel.ViewState>()
        viewModel.viewState.observeForever {
            latch.countDown()
            actual.add(it)
        }
        latch.await(3, TimeUnit.SECONDS)
        viewModel.load()
        testScheduler.triggerActions()

        val loadingViewState = AdViewModel.ViewState(isLoaded = false)
        assert(actual[0] == loadingViewState)

        val errorViewState = AdViewModel.ViewState(isLoaded = true, isError = true)
        assert(actual[1] == errorViewState)

        verify(exactly = 1) { repository.get() }
    }
}
