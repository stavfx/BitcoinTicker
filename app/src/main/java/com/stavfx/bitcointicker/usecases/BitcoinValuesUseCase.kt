package com.stavfx.bitcointicker.usecases

import com.stavfx.bitcointicker.network.BitcoinTickerApi
import io.reactivex.Single
import javax.inject.Inject

interface BitcoinValuesUseCase {
   fun getBitcoinValues(): Single<BitcoinValues>
}

class BitcoinValuesUseCaseImpl @Inject constructor(
   api: BitcoinTickerApi
) : BitcoinValuesUseCase {

   // This will make sure that concurrent calls will be shared, and when no call is ongoing,
   // we'll hit the API again.
   // However this doesn't guarantee only 1 API hit per second, as we are only preventing concurrent
   // API calls, so if each call completes in 100ms, we'll be able to hit the api ~10 times / sec.
   // Let's fix that in the next commit.
   private val bitcoinValues: Single<BitcoinValues> = api.getBitcoinPrices()
      .map { it.toEntity() }
      .toObservable()
      .share()
      .firstOrError()

   override fun getBitcoinValues(): Single<BitcoinValues> {
      return bitcoinValues
   }
}
