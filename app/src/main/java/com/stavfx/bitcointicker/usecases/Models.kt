package com.stavfx.bitcointicker.usecases

/*
   This layer is pretty much a 1:1 of the network layer at this point, but it allows us some more
   flexibility in the future, if we want to present the data in a different way.
   This also allows us to not have to deal with nullable values from this point on.
 */

data class BitcoinValues(
   val usd: BitcoinValue,
   val cad: BitcoinValue,
   val aud: BitcoinValue,
)

data class BitcoinValue(
   val price: Double,
   val symbol: String,
)
