package com.example.apitest.retrofit

import com.example.apitest.response.ApiResponse
import com.example.apitest.response.DetailResponse
import com.example.apitest.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getApi(
        @Query("q") q : String,
//        @Header("Authorization") token: String = AUTH
    ) : Call<ApiResponse>

    @GET("users/{username}")
    fun getDetail(
        @Path("username") username : String,
//        @Header("Authorization") token: String = AUTH
    ) : Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String,
//        @Header("Authorization") token: String = AUTH
    ) : Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String,
//        @Header("Authorization") token: String = AUTH
    ) : Call<List<ItemsItem>>

//    companion object {
//        const val AUTH = "token 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"
//    }
}