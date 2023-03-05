package ru.veronikarepina.giphy.data.models

import androidx.room.ColumnInfo

data class Downsized(
    @ColumnInfo(name = "height_downsized")
    val height: String?,
    @ColumnInfo(name = "size_downsized")
    val size: String?,
    @ColumnInfo(name = "url_downsized")
    val url: String?,
    @ColumnInfo(name = "width_downsized")
    val width: String?
)
