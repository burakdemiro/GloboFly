package com.bydmr.globofly.services

import com.bydmr.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    @GET("destination")
    fun getDestinationList(@QueryMap filter: HashMap<String, String>): Call<List<Destination>>

    // Path parameters
    // Dynamic olarak parametre ile gelir parametre Pathdeki ile serialize olur ve üstteki id yerine geçer
    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    // Retrofit içerisindeki converter objeyi json'a kendi çevirecektir
    // Ayrıca türe göre header'ı kendisi ele alacak (application/json)
    @POST("destination")
    fun addDestination(@Body newDestination: Destination): Call<Destination>

    // key-value ikilisi formunda encode yapar
    // update yaparken formurlencoded formatında olur
    // response json formatında gelir
    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(@Path("id") id: Int,
                          @Field("city") city: String,
                          @Field("description") desc: String,
                          @Field("country") country: String): Call<Destination>


}