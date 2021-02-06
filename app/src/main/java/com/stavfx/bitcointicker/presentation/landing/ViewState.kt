package com.stavfx.bitcointicker.presentation.landing

/**
 * A class representing the full state of the UI at any given moment.
 * This allows us to also cache this value for super fast restoration of the previous state, upon
 * activity recreation / process death.
 */
data class ViewState(
   val bitCoinValue: String,
   val timestamp: String
)
