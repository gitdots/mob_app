package com.example.myapplication

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

private const val BASE_URL = "https://probet.live/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MarsApiService {
    @GET("?")
    suspend fun getObject(
        @Query("querry") query : String,
        @Query("data") data : String
    ): MyJsonObject
}

data class MyJsonObject(
    @Json(name = "predictii") val predictii: List<Predictie>,
    @Json(name = "bilete") val bilete: List<Bilet>)

data class Predictie(
    @Json(name ="country") val country : String,
    @Json(name ="league") val league : String,
    @Json(name ="meci") val meci : String,
    @Json(name ="betType") val betType : String,
    @Json(name ="betValue") val betValue : String,
    @Json(name ="betOdds") val betOdds : String,
    @Json(name ="highRisk") val highRisk : String,
    @Json(name ="ico") val ico : String)

data class Bilet(
    @Json(name ="title") val title: String,
    @Json(name ="content") val content : List<BiletContent>)

data class BiletContent(
    @Json(name ="country") val country : String,
    @Json(name ="league") val league : String,
    @Json(name ="meci") val meci : String,
    @Json(name ="betType") val betType : String,
    @Json(name ="betValue") val betValue : String,
    @Json(name ="betOdds") val betOdds : String,
    @Json(name ="ico") val ico : String
)

object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}