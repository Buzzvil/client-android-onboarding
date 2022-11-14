package com.buzzvil.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.buzzvil.onboarding.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MAIN_ACTIVITY"
    }
    private val buzzAdClient = BuzzAdClient()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buzzAdClient.get().enqueue(object: Callback<Ad> {
            override fun onResponse(call: Call<Ad>, response: Response<Ad>) {
                val result = response.body()
                binding.textView.text = result.toString()
            }

            override fun onFailure(call: Call<Ad>, t: Throwable) {
                Log.e(TAG, "Load Ads Failure", t)
                t.printStackTrace()
            }
        })
    }
}
