package ru.veronikarepina.giphy.data.models

import androidx.room.ColumnInfo

data class Original (
    @ColumnInfo(name = "url_Original")
    val url: String,
)
