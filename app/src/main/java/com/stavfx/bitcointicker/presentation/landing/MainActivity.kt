package com.stavfx.bitcointicker.presentation.landing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
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
   private var adapter: BtcAdapter? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      adapter = BtcAdapter()
      binding.btcViewpager.adapter = adapter

      binding.btcViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
         override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val viewState = lastViewState ?: return
            if (viewState.index != position) {
               lastViewState = viewState.copy(index = position)
            }
         }
      })
   }

   override fun onResume() {
      super.onResume()
      viewModel.observeViewState
         .map {
            // Carry over index from previous viewState (viewModel doesn't know about selected index)
            it.copy(index = lastViewState?.index ?: it.index)
         }
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
      adapter?.setState(this)
      binding.btcViewpager.setCurrentItem(index, false)
   }
}

private const val VIEW_STATE_KEY = "viewState"
