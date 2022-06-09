package com.rasil.viewmodel1.model

import com.google.gson.annotations.SerializedName

data class Albums(

    @field:SerializedName("Album")
    val album: ArrayList<AlbumItem>
)

data class AlbumItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("userId")
    val userId: Int
)
