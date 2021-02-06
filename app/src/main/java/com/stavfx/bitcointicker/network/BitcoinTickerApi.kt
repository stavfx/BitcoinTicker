package com.stavfx.bitcointicker.network

import io.reactivex.Single
import retrofit2.http.GET

interface BitcoinTickerApi {
   @GET("ticker")
   fun getBitcoinPrices(): Single<NetworkTickerResponse>
}
