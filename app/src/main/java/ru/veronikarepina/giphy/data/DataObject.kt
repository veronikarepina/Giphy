package ru.veronikarepina.giphy.data

import android.content.Context
import ru.veronikarepina.giphy.data.db.MainDb
import ru.veronikarepina.giphy.data.repository.LocalRepository
import ru.veronikarepina.giphy.data.repository.RemoteRepository

object DataObject {
    lateinit var dataBase: MainDb
    lateinit var remoteRepository: RemoteRepository
    lateinit var localRepository: LocalRepository

    fun initData(context: Context) {
        dataBase = MainDb.getDb(context)
        remoteRepository = RemoteRepository()
        localRepository = LocalRepository(dataBase.getDao())
    }
}