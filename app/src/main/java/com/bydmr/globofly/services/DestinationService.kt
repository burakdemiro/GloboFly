package com.bydmr.globofly.services

import com.bydmr.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface DestinationService {

    @GET("destination")
    fun getDestinationList(@QueryMap filter: HashMap<String, String>): Call<List<Destination>>

    // Path parameters
    // Dynamic olarak parametre ile gelir parametre Pathdeki ile serialize olur ve üstteki id yerine geçer
    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>


}