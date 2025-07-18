// FeedModule.kt
package com.sonusid.mello.di

import androidx.lifecycle.ViewModel
import com.sonusid.mello.presentation.homescreen.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
abstract class FeedModule {
    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindFeedViewModel(viewModel: FeedViewModel): ViewModel
}
