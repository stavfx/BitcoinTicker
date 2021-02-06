package com.stavfx.bitcointicker.usecases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {
   @Binds
   @Singleton
   fun bindBitcoinValuesUseCase(impl: BitcoinValuesUseCaseImpl): BitcoinValuesUseCase
}
