package com.rasil.viewmodel1

import com.rasil.viewmodel1.model.Albums
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>
}