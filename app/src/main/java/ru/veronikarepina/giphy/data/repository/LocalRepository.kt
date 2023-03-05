package ru.veronikarepina.giphy.data.repository

import androidx.lifecycle.LiveData
import ru.veronikarepina.giphy.data.db.GiphyDao
import ru.veronikarepina.giphy.data.models.Data

class LocalRepository(private val dao: GiphyDao) {
    fun getFavoriteGiphyFromDb(): LiveData<List<Data>> = dao.getFavoriteGiphy()

    suspend fun addGiphy(item: Data) {
        dao.insert(item)
    }

    suspend fun removeGiphy(item: Data) {
        dao.delete(item)
    }
}