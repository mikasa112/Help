package com.example.help.api

import com.example.help.app.App
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

/**
 * @author yuanan
 * @date 2023/5/8
 * @desc 网络请求
 */
interface HelpApi {

    @POST("api/auth/login")
    suspend fun login(): Response<ResponseData<String>>

    /**
     * 单例
     */
    companion object {
        private const val BASE_URL = "http://192.168.1.110:8080/"
        private const val BEARER = "Bearer ";
        operator fun invoke(): HelpApi {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", BEARER + App.getString("token"))
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HelpApi::class.java)
        }
    }
}