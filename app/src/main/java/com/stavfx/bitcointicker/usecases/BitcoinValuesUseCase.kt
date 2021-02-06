package com.stavfx.bitcointicker.usecases

import com.stavfx.bitcointicker.network.BitcoinTickerApi
import io.reactivex.Single
import javax.inject.Inject

interface BitcoinValuesUseCase {
   fun getBitcoinValues(): Single<BitcoinValues>
}

class BitcoinValuesUseCaseImpl @Inject constructor(
   private val api: BitcoinTickerApi
) : BitcoinValuesUseCase {
   override fun getBitcoinValues(): Single<BitcoinValues> {
      return api.getBitcoinPrices()
         .map { it.toEntity() }
   }
}
