package com.stavfx.bitcointicker.network.models

import com.google.gson.annotations.SerializedName

class BitcoinValue {
   @SerializedName("last")
   var price: Double? = null
   var symbol: String? = null
}
