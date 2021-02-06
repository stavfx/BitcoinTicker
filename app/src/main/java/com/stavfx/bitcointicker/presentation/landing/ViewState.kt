package com.stavfx.bitcointicker.presentation.landing

import android.os.Bundle

/**
 * A class representing the full state of the UI at any given moment.
 * This allows us to also cache this value for super fast restoration of the previous state, upon
 * activity recreation / process death.
 */
data class ViewState(
   val bitCoinValue: String,
   val timestamp: String
) {
   // from/to bundle could be replaced with @Parcelize or something,
   // but let's just do it manually for now
   companion object {
      private const val BTC_KEY = "vs_btc"
      private const val TIMESTAMP_KEY = "vs_timestamp"
      fun fromBundle(bundle: Bundle): ViewState? {
         if (!bundle.containsKey(BTC_KEY) || !bundle.containsKey(TIMESTAMP_KEY)) {
            return null
         }
         return ViewState(
            bitCoinValue = bundle.getString(BTC_KEY, ""),
            timestamp = bundle.getString(TIMESTAMP_KEY, "")
         )
      }
   }

   fun toBundle(bundle: Bundle) {
      bundle.putString(BTC_KEY, bitCoinValue)
      bundle.putSerializable(TIMESTAMP_KEY, timestamp)
   }
}
