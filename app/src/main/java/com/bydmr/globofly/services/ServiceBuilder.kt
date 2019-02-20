package com.bydmr.globofly.services

import android.os.Build
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object ServiceBuilder {

    private const val URL = "http://10.0.2.2:9000/"

    // Webservis işlemlerinde header, response code, body, lengh vs birçok ifadeyi logcat'te görmeni sağlar
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create a Custom Interceptor to apply Headers application wide
    // Interceptor 4 işlemde kullanılır
    // Logging
    // Http Headers
    // Authentication
    // Error Handling
    val headerInterceptor = object : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            var request = chain.request()
            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .build()

            val response = chain.proceed(request)
            return response
        }

    }

    // retrofit işlemlerini gerçekleştirebilmesi için okHtpp'ye ihtiyaç duyar aynı zamanda interceptor gibi pluggable'ları kullanabilmesi için gereklidir
    private val okHtpp = OkHttpClient.Builder().addInterceptor(headerInterceptor).addInterceptor(logger)

    // Pastayı oluşturma aşaması araları muzlu
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHtpp.build())

    // Genel bir pasta kalıbı çıkarma
    private val retrofit = builder.build()

    // Burada da üstüne koyduğun meyveye göre kirazlı mı çilekli mi gibi
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}