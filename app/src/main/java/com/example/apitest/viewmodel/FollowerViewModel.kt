package com.example.apitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apitest.response.ItemsItem
import com.example.apitest.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel(){
    private val _apiDatass = MutableLiveData<List<ItemsItem>>()
    val apiDatass: LiveData<List<ItemsItem>> = _apiDatass

    private val _showErrorss: MutableLiveData<Boolean> = MutableLiveData(false)
    val showErrorss: LiveData<Boolean> = _showErrorss

    private val _isLoadingss = MutableLiveData<Boolean>()
    val isLoadingss: LiveData<Boolean> = _isLoadingss

    private val _showFailedss: MutableLiveData<Boolean> = MutableLiveData(false)
    val showFailedss: LiveData<Boolean> = _showFailedss

    fun followApi(user: String) {
        _isLoadingss.value = true
        _showErrorss.value = false
        _showFailedss.value = false
        val client = ApiConfig.getApiService().getFollowing(user)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoadingss.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody!!.isNotEmpty()){
                    _apiDatass.value = responseBody!!
                } else {
                    _showErrorss.value = true
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoadingss.value = false
                _showFailedss.value = true
            }
        })
    }
}