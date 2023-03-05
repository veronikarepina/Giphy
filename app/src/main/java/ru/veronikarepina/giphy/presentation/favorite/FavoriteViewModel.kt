package ru.veronikarepina.giphy.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.veronikarepina.giphy.data.DataObject
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.data.repository.LocalRepository

class FavoriteViewModel : ViewModel() {

    private val localRepository: LocalRepository = DataObject.localRepository

    val favoriteGiphy = localRepository.getFavoriteGiphyFromDb()

    fun deleteFavorite(gif: Data) = viewModelScope.launch {
        localRepository.removeGiphy(gif)
    }
}