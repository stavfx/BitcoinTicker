package com.stavfx.bitcointicker.presentation.landing

import androidx.lifecycle.ViewModel
import com.stavfx.bitcointicker.usecases.BitcoinValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class BitcoinPricesViewModel @Inject constructor(
   private val bitcoinValuesUseCase: BitcoinValuesUseCase
) : ViewModel() {
   fun observeViewState(): Observable<ViewState> {
      return Observable.interval(0, 1, TimeUnit.SECONDS)
         // Poll the API every second. If the previous API call hasn't finished within 1s,
         // it'll be dropped (this can/will be optimized at the UseCase layer)
         .switchMapSingle { bitcoinValuesUseCase.getBitcoinValues() }
         .map { values ->
            // Only show USD initially
            ViewState(
               "${values.usd.price} BTC/USD", // of course this should be extracted to strings.xml
               Date().toString() // format if you want
            )
         }
   }
}
