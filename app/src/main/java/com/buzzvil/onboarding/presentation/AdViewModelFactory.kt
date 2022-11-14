package com.buzzvil.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.buzzvil.onboarding.domain.repository.AdRepository
import javax.inject.Inject

class AdViewModelFactory @Inject constructor(
    private val repository: AdRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AdViewModel::class.java)){
            return AdViewModel(repository) as T
        }
        throw IllegalArgumentException("unKnown ViewModel class")
    }
}
