package ru.veronikarepina.giphy.presentation.first

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.veronikarepina.giphy.data.DataObject
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.utils.asLiveData

class MainViewModel : ViewModel() {

    private val remoteRepository = DataObject.remoteRepository
    private val localRepository = DataObject.localRepository

    private val _giphy = MutableLiveData<List<Data>>()
    val giphy = _giphy.asLiveData()

    val favoriteGiphy = localRepository.getFavoriteGiphyFromDb()

    init {
        getGiphyData("vk")
    }

    fun getGiphyData(searchText:String) = viewModelScope.launch {
        val response = withContext(Dispatchers.IO) {
            remoteRepository.getGiphy(searchWord = searchText)
        }
        _giphy.value = response.data
        handleAllGiphys(favoriteGiphy.value)
    }

    fun handleAllGiphys(list: List<Data>?) { // 2 favorite
        _giphy.value?.map { it.copy(isFavorite = false) }?.toMutableList()
            ?.onEach { gif ->
                list?.any { it.id == gif.id }?.let { gif.isFavorite = it }
            }?.let { _giphy.value = it }
    }

    fun handleFavorites(gif: Data) {
        if (gif.isFavorite) {
            deleteFavorite(gif)
        } else {
            addFavorite(gif)
        }
    }

    private fun deleteFavorite(gif: Data) = viewModelScope.launch {
        localRepository.removeGiphy(gif)
        val oldList = _giphy.value?.toMutableList()
        _giphy.value =
            oldList?.map {
                it.copy(
                    isFavorite = if (gif.id == it.id) {
                        false
                    } else {
                        it.isFavorite
                    }
                )
            }
    }

    private fun addFavorite(gif: Data) = viewModelScope.launch {
        localRepository.addGiphy(gif.copy(isFavorite = true))
    }
}

