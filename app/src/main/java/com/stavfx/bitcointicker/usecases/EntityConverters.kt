package com.stavfx.bitcointicker.usecases

import com.stavfx.bitcointicker.network.NetworkBitcoinValue
import com.stavfx.bitcointicker.network.NetworkTickerResponse

/*
   These could be actual objects provided by DI, but at this stage we can keep it simple
 */

fun NetworkTickerResponse.toEntity(): BitcoinValues = BitcoinValues(
   listOfNotNull(
      usd?.toEntity(Currency.USD),
      cad?.toEntity(Currency.CAD),
      aud?.toEntity(Currency.AUD),
      brl?.toEntity(Currency.BRL),
      chf?.toEntity(Currency.CHF),
   )
)

fun NetworkBitcoinValue.toEntity(currency: Currency) = BitcoinValue(
   price = requireNotNull(price),
   currency = currency
)
