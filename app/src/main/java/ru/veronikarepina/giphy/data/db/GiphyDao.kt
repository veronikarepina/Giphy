package ru.veronikarepina.giphy.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.veronikarepina.giphy.data.models.Data

@Dao
interface GiphyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Data)

    @Delete()
    suspend fun delete(item: Data)

    @Query("SELECT * FROM giphy")
    fun getFavoriteGiphy(): LiveData<List<Data>>
}