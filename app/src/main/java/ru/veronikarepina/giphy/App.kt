package ru.veronikarepina.giphy

import android.app.Application
import ru.veronikarepina.giphy.data.DataObject

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        DataObject.initData(this)
    }
}