package com.example.planet.fr.you.test.di

import com.example.planet.fr.you.test.data.remote.RetrofitService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://reqres.in/api/"

val retrofitModule = module {
    single { okHttp() }
    single { retrofit(BASE_URL) }
    single { get<Retrofit>().create(RetrofitService::class.java) }
}

private fun okHttp() = OkHttpClient.Builder().build()

private fun retrofit(baseUrl: String) = Retrofit.Builder()
    .client(okHttp())
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()