package com.stavfx.bitcointicker.usecases

import com.stavfx.bitcointicker.network.BitcoinTickerApi
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface BitcoinValuesUseCase {
   fun getBitcoinValues(): Single<BitcoinValues>
}

class BitcoinValuesUseCaseImpl @Inject constructor(
   api: BitcoinTickerApi
) : BitcoinValuesUseCase {

   /**
    * A hot observable that emits every 1s from first subscription until the end of time.
    */
   private val interval = Observable.interval(0, 1, TimeUnit.SECONDS)
      .publish()
      .autoConnect()

   // Waits for `interval` to emit before making an api request (which only happens once / second)
   // Then all the calls that came in while waiting are sharing the same response.
   private val bitcoinValues: Single<BitcoinValues> =
      interval
         .firstOrError()
         .flatMap { api.getBitcoinPrices() }
         .map { it.toEntity() }
         .toObservable()
         .share()
         .firstOrError()

   override fun getBitcoinValues(): Single<BitcoinValues> {
      return bitcoinValues
   }
}
