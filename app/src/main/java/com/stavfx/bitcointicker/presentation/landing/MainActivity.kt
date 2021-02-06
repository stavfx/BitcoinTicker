package com.stavfx.bitcointicker.presentation.landing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stavfx.bitcointicker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private val viewModel by viewModels<BitcoinPricesViewModel>()
   private lateinit var binding: ActivityMainBinding

   private val disposables = CompositeDisposable()
   private var lastViewState: ViewState? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)
   }

   override fun onResume() {
      super.onResume()
      viewModel.observeViewState()
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe { viewState ->
            lastViewState = viewState
            viewState.render()
         }
         .addTo(disposables)
   }

   override fun onSaveInstanceState(outState: Bundle) {
      outState.putParcelable(VIEW_STATE_KEY, lastViewState)
      super.onSaveInstanceState(outState)
   }

   override fun onRestoreInstanceState(savedInstanceState: Bundle) {
      super.onRestoreInstanceState(savedInstanceState)
      // Restore last state if it exists
      lastViewState = savedInstanceState.getParcelable(VIEW_STATE_KEY)
      lastViewState?.render()
   }

   override fun onPause() {
      super.onPause()
      disposables.clear()
   }

   private fun ViewState.render() {
      binding.txtBtc.text = bitCoinValue
      binding.txtTimestamp.text = timestamp
   }
}

private const val VIEW_STATE_KEY = "viewState"
