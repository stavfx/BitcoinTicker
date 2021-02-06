package com.stavfx.bitcointicker.usecases

import com.stavfx.bitcointicker.network.NetworkBitcoinValue
import com.stavfx.bitcointicker.network.NetworkTickerResponse

/*
   These could be actual objects provided by DI, but at this stage we can keep it simple
 */

fun NetworkTickerResponse.toEntity() = BitcoinValues(
   usd = requireNotNull(usd).toEntity(),
   cad = requireNotNull(cad).toEntity(),
   aud = requireNotNull(aud).toEntity(),
)

fun NetworkBitcoinValue.toEntity() = BitcoinValue(
   price = requireNotNull(price),
   symbol = requireNotNull(symbol)
)
