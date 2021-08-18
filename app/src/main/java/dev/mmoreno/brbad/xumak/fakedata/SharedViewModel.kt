package dev.mmoreno.brbad.xumak.fakedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class SharedViewModel : ViewModel() {
  val charactersLiveData = liveData{
    emit(characters)
  }


}