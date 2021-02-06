package com.stavfx.bitcointicker.network

import com.stavfx.bitcointicker.network.models.BitcoinTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BitcoinTickerApi {
   @GET("ticker")
   fun getBitcoinPrices(): Single<BitcoinTickerResponse>
}
