package com.stavfx.bitcointicker.presentation.landing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stavfx.bitcointicker.databinding.PageBtcBinding

class BtcAdapter : RecyclerView.Adapter<BtcAdapter.ViewHolder>() {
   private val pages = mutableListOf<ViewStatePage>()

   init {
      setHasStableIds(true)
   }

   fun setState(viewState: ViewState) {
      pages.clear()
      pages.addAll(viewState.pages)
      // TODO restore selected index
      notifyDataSetChanged()
   }

   override fun getItemId(position: Int): Long {
      return if (position.isValid()) {
         pages[position].currency.currencyName.toLong()
      } else {
         0
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val binding = PageBtcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ViewHolder(binding)
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      if (position.isValid()) {
         holder.bind(pages[position])
      }
   }

   override fun getItemCount(): Int {
      return pages.size
   }

   private fun Int.isValid() = this in (0 until pages.size)

   class ViewHolder(private val binding: PageBtcBinding) : RecyclerView.ViewHolder(binding.root) {
      fun bind(page: ViewStatePage) {
         binding.txtBtc.text = page.bitCoinValue
         binding.txtTimestamp.text = page.timestamp
      }
   }
}
