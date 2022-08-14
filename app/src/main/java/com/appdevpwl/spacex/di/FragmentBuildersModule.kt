package com.appdevpwl.spacex.di


import com.appdevpwl.spacex.ui.SettingsFragment
import com.appdevpwl.spacex.ui.capsule.CapsuleFragment
import com.appdevpwl.spacex.ui.company.AboutCompanyFragment
import com.appdevpwl.spacex.ui.cores.CoresDetailsFragment
import com.appdevpwl.spacex.ui.cores.CoresFragment
import com.appdevpwl.spacex.ui.home.HomeFragment
import com.appdevpwl.spacex.ui.launches.LaunchesDetailsFragment
import com.appdevpwl.spacex.ui.launches.LaunchesFragment
import com.appdevpwl.spacex.ui.launches.PastLaunchesFragment
import com.appdevpwl.spacex.ui.launches.UpcomingLaunchesFragment
import com.appdevpwl.spacex.ui.rocket.RocketDetailsFragment
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

    @ContributesAndroidInjector
    abstract fun contributeRocketDetailsFragment(): RocketDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeLaunchesFragment(): LaunchesFragment

    @ContributesAndroidInjector
    abstract fun contributeUpcomingLaunchesFragment(): UpcomingLaunchesFragment

    @ContributesAndroidInjector
    abstract fun contributePastLaunchesFragment(): PastLaunchesFragment

    @ContributesAndroidInjector
    abstract fun contributeCoresFragment(): CoresFragment

    @ContributesAndroidInjector
    abstract fun contributeLaunchesDetailsFragment(): LaunchesDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeAboutCompanyFragment(): AboutCompanyFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeCoresDetailsFragment(): CoresDetailsFragment


}