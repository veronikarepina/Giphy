package ru.veronikarepina.giphy.data.models

import androidx.room.ColumnInfo

data class DownsizedMedium(
    @ColumnInfo(name = "height_DownsizedMedium")
    val height: String,
    @ColumnInfo(name = "size_DownsizedMedium")
    val size: String,
    @ColumnInfo(name = "url_DownsizedMedium")
    val url: String,
    @ColumnInfo(name = "width_DownsizedMedium")
    val width: String
)
