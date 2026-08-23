package vendaway.housewares.vendawayhomego.di

import vendaway.housewares.vendawayhomego.data.datastore.OJFXTOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { OJFXTOnboardingPrefs(androidContext()) }
}