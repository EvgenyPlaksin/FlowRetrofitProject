package com.example.flowretrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.flowretrofitproject.databinding.ActivityMainBinding
import com.example.flowretrofitproject.utils.NetworkResult
import com.example.flowretrofitproject.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {  fetchData() }

    }

    private fun fetchData() {
        viewModel.init()
        // обязательно использовать лаунч вен стартид
        lifecycleScope.launchWhenStarted {
            viewModel.response.collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        binding.imageView.load(response.data?.message) {
                            placeholder(R.drawable.ic_baseline_error_24)
                        }
                    }
                    is NetworkResult.Error -> {
                        // show error message
                    }
                    is NetworkResult.Loading -> {
                        // show a progress bar
                    }
                }
            }

        }

    }
}