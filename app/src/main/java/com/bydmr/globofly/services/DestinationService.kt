package com.bydmr.globofly.services

import com.bydmr.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DestinationService {

    @GET("destination")
    fun getDestinationList(@Query("country") country: String): Call<List<Destination>> // null geçersen parametresiz gibi davranır

    // Path parameters
    // Dynamic olarak parametre ile gelir parametre Pathdeki ile serialize olur ve üstteki id yerine geçer
    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>


}