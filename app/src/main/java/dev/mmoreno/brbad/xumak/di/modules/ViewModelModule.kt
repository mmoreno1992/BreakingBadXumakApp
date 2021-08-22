package dev.mmoreno.brbad.xumak.di.modules

import dev.mmoreno.brbad.xumak.fakedata.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { SharedViewModel(get()) }
}