package com.example.zetutorial.data.remote.response

data class CharacterResponse(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailResponse
)
