package com.stavfx.bitcointicker.usecases

import androidx.annotation.StringRes
import com.stavfx.bitcointicker.R

/*
   This layer is pretty much a 1:1 of the network layer at this point, but it allows us some more
   flexibility in the future, if we want to present the data in a different way.
   This also allows us to not have to deal with nullable values from this point on.
 */

data class BitcoinValues(
   val values: List<BitcoinValue>
)

data class BitcoinValue(
   val price: Double,
   val currency: Currency
)

enum class Currency(@StringRes val currencyName: Int) {
   USD(R.string.usd),
   CAD(R.string.cad),
   AUD(R.string.aud),
   BRL(R.string.brl),
   CHF(R.string.chf),
}
