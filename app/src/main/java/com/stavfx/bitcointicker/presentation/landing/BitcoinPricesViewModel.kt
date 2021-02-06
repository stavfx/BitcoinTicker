package com.stavfx.bitcointicker.presentation.landing

import androidx.lifecycle.ViewModel
import com.stavfx.bitcointicker.usecases.BitcoinValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BitcoinPricesViewModel @Inject constructor(
   bitcoinValuesUseCase: BitcoinValuesUseCase
) : ViewModel() {
}
