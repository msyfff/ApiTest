package com.example.apitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apitest.response.ItemsItem
import com.example.apitest.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentViewModel : ViewModel() {
    private val _apiDatasss = MutableLiveData<List<ItemsItem>>()
    val apiDatasss: LiveData<List<ItemsItem>> = _apiDatasss

    private val _showErrorsss: MutableLiveData<Boolean> = MutableLiveData(false)
    val showErrorsss: LiveData<Boolean> = _showErrorsss

    private val _isLoadingsss = MutableLiveData<Boolean>()
    val isLoadingsss: LiveData<Boolean> = _isLoadingsss

    private val _showFailedsss: MutableLiveData<Boolean> = MutableLiveData(false)
    val showFailedsss: LiveData<Boolean> = _showFailedsss

    fun followApi(user: String) {
        _isLoadingsss.value = true
        _showErrorsss.value = false
        _showFailedsss.value = false
        val client = ApiConfig.getApiService().getFollowers(user)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoadingsss.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody!!.isNotEmpty()) {
                    _apiDatasss.value = responseBody!!
                } else {
                    _showErrorsss.value = true
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoadingsss.value = false
                _showFailedsss.value = true
            }
        })
    }
}