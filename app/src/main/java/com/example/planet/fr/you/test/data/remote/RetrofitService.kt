package com.example.planet.fr.you.test.data.remote

import com.example.planet.fr.you.test.data.remote.dto.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("users?page=2")
    suspend fun getPersons(): Response
}