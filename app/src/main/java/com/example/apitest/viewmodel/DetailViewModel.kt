package com.example.apitest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apitest.response.DetailResponse
import com.example.apitest.response.ItemsItem
import com.example.apitest.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _setDetailData = MutableLiveData<DetailResponse>()
    val setDetailData: LiveData<DetailResponse> = _setDetailData

    companion object {
        private const val TAG = "MainActivity"
    }

    fun findApi(user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetail(user)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _setDetailData.value = responseBody!!
                    } else Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onResponse: ${t.message}")
            }
        })
    }
}

