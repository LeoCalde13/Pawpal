package com.caldeira.pawpal.data

import com.caldeira.pawpal.model.CatDto
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = ("https://api.thecatapi.com/v1/")

interface CatsApi {

    @Headers("Content-Type: application/json")
    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int, @Query("page") page: Int): Response<List<CatDto>>

    companion object {
        operator fun invoke(): CatsApi {
            val okkHttpclient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okkHttpclient)
                .build()
                .create(CatsApi::class.java)
        }
    }
}