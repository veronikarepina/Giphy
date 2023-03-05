package ru.veronikarepina.giphy.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.veronikarepina.giphy.data.DataObject
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.utils.asLiveData

class DetailViewModel: ViewModel() {

    private val localRepository = DataObject.localRepository

    private val _detailGiphy = MutableLiveData<Data>()
    val detailGiphy = _detailGiphy.asLiveData()

    fun setDetail(gif: Data) {
        _detailGiphy.value = gif
    }

    fun handleFavorite() {
        val gif = _detailGiphy.value ?: return
        if (gif.isFavorite) {
            deleteFavorite(gif)
        } else {
            addToFavorite(gif)
        }
    }
    private fun addToFavorite(gif: Data) {
        val actualGif = gif.copy(isFavorite = true)
        setDetail(actualGif)
        viewModelScope.launch {
            localRepository.addGiphy(actualGif)
        }
    }

    private fun deleteFavorite(gif: Data) {
        val actualGif = gif.copy(isFavorite = false)
        setDetail(actualGif)
        viewModelScope.launch {
            localRepository.removeGiphy(gif.copy(isFavorite = true))
        }
    }
}

