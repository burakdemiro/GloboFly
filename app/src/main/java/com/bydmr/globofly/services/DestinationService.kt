package com.bydmr.globofly.services

import com.bydmr.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    // Gönderilen header'ları server tarafından karşılayabilirsin (isteğe bağlı) ve ona göre işlem yaptırabilirsin
    // Mesela Android cihazı bağlandı gibi konsola yazı yazdırmak
    @Headers("x-device-type: Android", "x-foo: bar") // Static headers
    @GET("destination")
    fun getDestinationList(@QueryMap filter: HashMap<String, String>,
                           @Header("Accept-Language") language: String): Call<List<Destination>> // Dynamic header

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

    // Bu işlemde Server'a veri gönderilmediği için RequestBody yoktur
    // Json'da yoktur
    // FormUrlEncoded'da yoktur
    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id:Int): Call<Unit>


}