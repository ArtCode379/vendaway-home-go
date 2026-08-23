package vendaway.housewares.vendawayhomego.di

import vendaway.housewares.vendawayhomego.ui.viewmodel.AppViewModel
import vendaway.housewares.vendawayhomego.ui.viewmodel.CartViewModel
import vendaway.housewares.vendawayhomego.ui.viewmodel.CheckoutViewModel
import vendaway.housewares.vendawayhomego.ui.viewmodel.OJFXTOnboardingVM
import vendaway.housewares.vendawayhomego.ui.viewmodel.OrderViewModel
import vendaway.housewares.vendawayhomego.ui.viewmodel.ProductDetailsViewModel
import vendaway.housewares.vendawayhomego.ui.viewmodel.ProductViewModel
import vendaway.housewares.vendawayhomego.ui.viewmodel.OJFXTSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        OJFXTSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        OJFXTOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}