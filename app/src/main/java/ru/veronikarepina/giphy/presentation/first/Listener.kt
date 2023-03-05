package ru.veronikarepina.giphy.presentation.first

import ru.veronikarepina.giphy.data.models.Data

interface Listener {
    fun onClick(item: Data)
    fun addFavorite(item: Data)
    fun deleteFavorite(item: Data)
}