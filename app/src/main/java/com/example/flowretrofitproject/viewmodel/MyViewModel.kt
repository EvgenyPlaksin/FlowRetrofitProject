package com.example.flowretrofitproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowretrofitproject.model.data.ApiResponse
import com.example.flowretrofitproject.model.repository.Repository
import com.example.flowretrofitproject.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
// наследуемся от андроид потому что нужен контекст
class MyViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableStateFlow<NetworkResult<ApiResponse>?> = MutableStateFlow(null)
    val response: StateFlow<NetworkResult<ApiResponse>?> = _response

    // это довольно странное решение, но допустимое. Даже в примере от гугла было подобное
    fun init(){
        fetchApiResponse()
    }

  private fun fetchApiResponse() = viewModelScope.launch {
        repository.getData().collect { values ->
            _response.value = values
        }
    }

}