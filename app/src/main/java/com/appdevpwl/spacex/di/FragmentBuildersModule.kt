package com.appdevpwl.spacex.di


import com.appdevpwl.spacex.ui.capsule.CapsuleFragment
import com.appdevpwl.spacex.ui.home.HomeFragment
import com.appdevpwl.spacex.ui.rocket.RocketFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCapsuleFragment(): CapsuleFragment

    @ContributesAndroidInjector
    abstract fun contributeRocketFragment(): RocketFragment



}