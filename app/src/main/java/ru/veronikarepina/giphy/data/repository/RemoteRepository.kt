package ru.veronikarepina.giphy.data.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.veronikarepina.giphy.data.api.ApiService
import ru.veronikarepina.giphy.data.models.Giphy

class RemoteRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.giphy.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient().newBuilder().apply { addInterceptor(getLoggingInterceptor()) }.build())
        .build()

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun getGiphy(searchWord:String): Giphy {
        return api.getGiphyApi(q = searchWord)
    }
}