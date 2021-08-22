package dev.mmoreno.brbad.xumak.fakedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity
import dev.mmoreno.brbad.xumak.paging.BreakingBadRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: BreakingBadRepository) : ViewModel() {

  @ExperimentalPagingApi
  fun getCharactersList(): LiveData<PagingData<BreakingBadCharacterEntity>> =
    repository.getBreakingBadCharactersList()

  fun setBreakingBadCharacterAsFavorite(characterId: Int, isFavorite: Boolean) =
    viewModelScope.launch {
      repository.setBreakingBadCharacterAsFavorite(characterId, isFavorite)
    }
}