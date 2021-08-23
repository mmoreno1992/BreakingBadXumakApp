package dev.mmoreno.brbad.xumak

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity
import dev.mmoreno.brbad.xumak.repositories.BreakingBadRepository
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: BreakingBadRepository) : ViewModel() {

  private var _networkStatus = MutableLiveData(false)
  private var _firstTimeNetworkStatusLoaded: Boolean = true
  val firstTimeNetworkStatusLoaded
    get() = _firstTimeNetworkStatusLoaded
  val networkStatus get() = _networkStatus

  @ExperimentalPagingApi
  fun getCharactersList(): LiveData<PagingData<BreakingBadCharacterEntity>> =
    repository.getBreakingBadCharactersList()

  fun setBreakingBadCharacterAsFavorite(characterId: Int, isFavorite: Boolean) =
    viewModelScope.launch {
      repository.setBreakingBadCharacterAsFavorite(characterId, isFavorite)
    }

  fun changeNetworkStatus(isOnline: Boolean) {
    _firstTimeNetworkStatusLoaded = false
    _networkStatus.postValue(isOnline)

  }
}