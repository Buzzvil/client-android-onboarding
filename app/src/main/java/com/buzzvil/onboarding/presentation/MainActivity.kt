package com.buzzvil.onboarding.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.buzzvil.onboarding.App
import com.buzzvil.onboarding.databinding.ActivityMainBinding
import com.buzzvil.onboarding.domain.model.Ad

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AdViewModel
    private lateinit var viewModelFactory: AdViewModelFactory
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelFactory = (application as App).container.provideAdViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[AdViewModel::class.java]
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this) {
            showAdState(it.ad)
            showLoadingState(it.isLoaded)
            showErrorState(it.isError)
        }
    }

    private fun showAdState(ad: Ad?) {
        binding.textView.text = ad?.toString()
    }

    private fun showLoadingState(isLoaded: Boolean) {
        binding.progressBar.visibility = if (isLoaded) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun showErrorState(isError: Boolean) {
       if (isError) {
           Toast.makeText(
               applicationContext,
               "Load Ad Failure",
               Toast.LENGTH_SHORT
           ).show()
       }
    }
}
