package ru.veronikarepina.giphy.data.models

import androidx.room.ColumnInfo

data class DownsizedLarge(
    @ColumnInfo(name = "height_DownsizedLarge")
    val height: String,
    @ColumnInfo(name = "size_DownsizedLarge")
    val size: String,
    @ColumnInfo(name = "url_DownsizedLarge")
    val url: String,
    @ColumnInfo(name = "width_DownsizedLarge")
    val width: String
)
