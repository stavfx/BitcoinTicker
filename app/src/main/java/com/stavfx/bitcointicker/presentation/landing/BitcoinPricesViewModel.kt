package com.stavfx.bitcointicker.presentation.landing

import android.content.Context
import androidx.lifecycle.ViewModel
import com.stavfx.bitcointicker.R
import com.stavfx.bitcointicker.usecases.BitcoinValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class BitcoinPricesViewModel @Inject constructor(
   bitcoinValuesUseCase: BitcoinValuesUseCase,
   @ApplicationContext appContext: Context
) : ViewModel() {
   // This never changes, so might as well fetch it only once
   private val btc = appContext.getString(R.string.btc)

   val observeViewState: Observable<ViewState> = Observable.interval(0, 1, TimeUnit.SECONDS)
      // Poll the API every second. If the previous API call hasn't finished within 1s,
      // it'll be dropped (this can/will be optimized at the UseCase layer)
      .switchMapSingle { bitcoinValuesUseCase.getBitcoinValues() }
      .map { values ->
         val pages = values.values.map {
            val currency = appContext.getString(it.currency.currencyName)
            ViewStatePage(
               bitCoinValue = "${it.price} $btc/$currency",
               timestamp = Date().toString(), // format if you want
               currency = it.currency
            )
         }
         ViewState(pages)
      }
      .retry() // If there's an error, we just want to try again. (Can be optimized in the future)
}
