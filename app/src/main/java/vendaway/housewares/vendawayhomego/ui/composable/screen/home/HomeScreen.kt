package vendaway.housewares.vendawayhomego.ui.composable.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import vendaway.housewares.vendawayhomego.R
import vendaway.housewares.vendawayhomego.data.model.Product
import vendaway.housewares.vendawayhomego.ui.composable.shared.OJFXTContentWrapper
import vendaway.housewares.vendawayhomego.ui.composable.shared.OJFXTEmptyView
import vendaway.housewares.vendawayhomego.ui.state.DataUiState
import vendaway.housewares.vendawayhomego.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    Column(modifier = modifier) {

        OJFXTContentWrapper(
            dataState = productsState,

            dataPopulated = {
                val data = (productsState as DataUiState.Populated).data
            },

            dataEmpty = {
                OJFXTEmptyView(
                    primaryText = stringResource(R.string.ojfxt_products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}