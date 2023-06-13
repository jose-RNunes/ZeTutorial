package com.example.zetutorial.data.remote.api

import com.example.zetutorial.data.remote.response.DataResponse

interface MarvelApi {

    suspend fun fetchCharacters(): DataResponse
}