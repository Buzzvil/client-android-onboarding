package com.buzzvil.onboarding.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzzvil.onboarding.domain.model.Ad
import com.buzzvil.onboarding.domain.repository.AdRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class AdViewModel(
    private val repository: AdRepository,
    private val schedulerProvider: SchedulerProvider
): ViewModel() {
    data class ViewState(
        val ad: Ad? = null,
        val isLoaded: Boolean = false,
        val isError: Boolean = false,
    )

    private val compositeDisposable = CompositeDisposable()
    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    val viewState: LiveData<ViewState>
        get() = _viewState

    fun load() {
        val loadingViewState = ViewState(isLoaded = false)
        _viewState.postValue(loadingViewState)
        val disposable = repository.get()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
            .subscribe({ ad ->
                val loadedViewState = ViewState(ad = ad, isLoaded = true)
                _viewState.postValue(loadedViewState)
            }, { throwable ->
                val errorViewState = ViewState(isError = true, isLoaded = true)
                _viewState.postValue(errorViewState)
                throwable.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
