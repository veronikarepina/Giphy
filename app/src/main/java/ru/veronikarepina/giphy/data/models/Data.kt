package ru.veronikarepina.giphy.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "giphy")
data class Data(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded
    val images: Images,
    val import_datetime: String,
    val is_sticker: Int,
    val rating: String,
    val slug: String,
    val source: String,
    val source_post_url: String,
    val source_tld: String,
    val title: String,
    val trending_datetime: String,
    val type: String,
    val url: String,
    var isFavorite: Boolean = false,
):java.io.Serializable
