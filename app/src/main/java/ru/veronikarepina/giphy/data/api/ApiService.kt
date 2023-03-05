package ru.veronikarepina.giphy.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.veronikarepina.giphy.data.models.Giphy

interface ApiService {
    @GET("/v1/gifs/search")
    suspend fun getGiphyApi(
        @Query("api_key") api_key: String = "pjKN31rGkmrAGvZMaXIQc0SgLzp608DN",
        @Query("q") q: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset:Int = 0,
        @Query("rating") rating: String = "r",
        @Query("lang") lang :String = "ru"
    ): Giphy
}