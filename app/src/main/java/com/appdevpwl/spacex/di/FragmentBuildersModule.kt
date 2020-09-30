package com.appdevpwl.spacex.di


import com.appdevpwl.spacex.ui.capsule.CapsuleFragment
import com.appdevpwl.spacex.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCapsuleFragment(): CapsuleFragment



}