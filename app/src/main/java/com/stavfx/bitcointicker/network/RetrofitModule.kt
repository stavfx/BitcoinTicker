package com.stavfx.bitcointicker.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://blockchain.info"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
   @Provides
   @Singleton
   fun bindRetrofit(): Retrofit {
      val logging = HttpLoggingInterceptor().apply {
         setLevel(HttpLoggingInterceptor.Level.BODY)
      }
      val httpClient = OkHttpClient.Builder()
         .addInterceptor(logging)

      return Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
         .client(httpClient.build())
         .build()
   }

   @Provides
   @Singleton
   fun bindTickerApi(retrofit: Retrofit): BitcoinTickerApi {
      return retrofit.create(BitcoinTickerApi::class.java)
   }
}
