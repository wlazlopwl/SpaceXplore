package com.appdevpwl.spacex.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appdevpwl.spacex.ui.capsule.CapsuleViewModel
import com.appdevpwl.spacex.ui.cores.CoresViewModel
import com.appdevpwl.spacex.ui.home.HomeViewModel
import com.appdevpwl.spacex.ui.launches.LaunchesViewModel
import com.appdevpwl.spacex.ui.rocket.RocketDetailsViewModel
import com.appdevpwl.spacex.ui.rocket.RocketViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CapsuleViewModel::class)
    abstract fun bindCapsuleViewModel(capsuleViewModel: CapsuleViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RocketViewModel::class)
    abstract fun bindRocketViewModel(rocketViewModel: RocketViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RocketDetailsViewModel::class)
    abstract fun bindRocketDetailsViewModel(rocketDetailsViewModel: RocketDetailsViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel::class)
    abstract fun bindLaunchesViewModel(launchesViewModel: LaunchesViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoresViewModel::class)
    abstract fun bindCoresViewModel(coresViewModel: CoresViewModel):ViewModel


    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}
