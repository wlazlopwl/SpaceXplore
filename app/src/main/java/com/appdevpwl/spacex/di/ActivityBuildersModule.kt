package com.appdevpwl.spacex.di



import com.appdevpwl.spacex.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


//(includes = [FragmentBuildersModule::class])
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}