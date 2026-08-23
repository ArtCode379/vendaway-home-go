package vendaway.housewares.vendawayhomego.di

import vendaway.housewares.vendawayhomego.data.repository.CartRepository
import vendaway.housewares.vendawayhomego.data.repository.OJFXTOnboardingRepo
import vendaway.housewares.vendawayhomego.data.repository.OrderRepository
import vendaway.housewares.vendawayhomego.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        OJFXTOnboardingRepo(
            ojfxtOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}