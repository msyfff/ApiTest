package com.example.apitest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apitest.response.ApiResponse
import com.example.apitest.response.ItemsItem
import com.example.apitest.retrofit.ApiConfig
import com.example.apitest.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _apiData = MutableLiveData<List<ItemsItem>>()
    val apiData: LiveData<List<ItemsItem>> = _apiData

    private val _showError: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showDefault: MutableLiveData<Boolean> = MutableLiveData(true)
    val showDefault: LiveData<Boolean> = _showDefault

    private val _showFailed: MutableLiveData<Boolean> = MutableLiveData(false)
    val showFailed: LiveData<Boolean> = _showFailed

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    companion object {
        const val TAG = "MainActivity"
    }

    fun findApi(user: String) {
        _isLoading.value = true
        _showDefault.value = false
        _showError.value = false
        val client = ApiConfig.getApiService().getApi(user)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val responseBody = response.body()
                _isLoading.value = false
                if (response.isSuccessful && responseBody?.items!!.isNotEmpty()) {
                    _apiData.value = (responseBody.items)
                    _snackbarText.value = Event(responseBody.totalCount)
                } else {
                    _apiData.value = (responseBody?.items)
                    _showError.postValue(true)
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                _isLoading.value = false
                _showFailed.value = true
                Log.e(TAG, "onResponse: ${t.message}")
            }
        })
    }
}