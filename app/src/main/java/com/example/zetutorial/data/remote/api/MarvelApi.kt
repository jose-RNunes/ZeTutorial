package com.example.zetutorial.data.remote.api

import com.example.zetutorial.data.remote.response.ResultResponse
import retrofit2.http.GET

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun fetchCharacters(): ResultResponse
}
