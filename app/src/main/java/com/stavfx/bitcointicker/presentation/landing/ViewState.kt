package com.stavfx.bitcointicker.presentation.landing

import android.os.Parcelable
import com.stavfx.bitcointicker.usecases.Currency
import kotlinx.parcelize.Parcelize

/**
 * A class representing the full state of the UI at any given moment.
 * This allows us to also cache this value for super fast restoration of the previous state, upon
 * activity recreation / process death.
 */
@Parcelize
data class ViewState(
   val pages: List<ViewStatePage>,
   val index: Int = 0
) : Parcelable

@Parcelize
data class ViewStatePage(
   val bitCoinValue: String,
   val timestamp: String,
   // Not really displayed in the view, but used as a unique identifier for each page
   val currency: Currency
) : Parcelable
