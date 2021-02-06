package com.stavfx.bitcointicker.network

import com.google.gson.annotations.SerializedName

/*
   For the time being, the models are small enough to fit in one file, but realistically
   they would go under a `models` package.

   These models are serving the network layer only and should be converted to "app-entities"
   before leaving the use-case layer
 */

class NetworkTickerResponse {
   @SerializedName("USD")
   var usd: NetworkBitcoinValue? = null

   @SerializedName("CAD")
   var cad: NetworkBitcoinValue? = null

   @SerializedName("AUD")
   var aud: NetworkBitcoinValue? = null

   @SerializedName("BRL")
   var brl: NetworkBitcoinValue? = null

   @SerializedName("CHF")
   var chf: NetworkBitcoinValue? = null
}

class NetworkBitcoinValue {
   @SerializedName("last")
   var price: Double? = null
   var symbol: String? = null
}
