package com.stavfx.bitcointicker.network.models

import com.google.gson.annotations.SerializedName

class BitcoinTickerResponse {
   @SerializedName("USD")
   var usd: BitcoinValue? = null

   @SerializedName("CAD")
   var cad: BitcoinValue? = null

   @SerializedName("AUD")
   var aud: BitcoinValue? = null
}

