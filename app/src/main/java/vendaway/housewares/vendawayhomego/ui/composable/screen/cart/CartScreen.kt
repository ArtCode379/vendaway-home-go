package vendaway.housewares.vendawayhomego.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import vendaway.housewares.vendawayhomego.ui.state.CartItemUiState
import vendaway.housewares.vendawayhomego.ui.state.DataUiState
import vendaway.housewares.vendawayhomego.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    val items = (state as? DataUiState.Populated)?.data.orEmpty()
    if (items.isEmpty()) {
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Your cart is ready for something useful", style = MaterialTheme.typography.titleLarge)
            Text("Start Shopping", color = MaterialTheme.colorScheme.primary)
        }
    } else {
        Column(modifier.padding(16.dp)) {
            Text("Your cart", style = MaterialTheme.typography.titleLarge)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(items, key = { it.productId }) { item ->
                    CartRow(
                        item = item,
                        onMinus = {
                            if (item.quantity == 1) viewModel.deleteFromCart(item.productId)
                            else viewModel.decrementItemInCart(item.productId)
                        },
                        onPlus = { viewModel.incrementProductInCart(item.productId) },
                        onRemove = { viewModel.deleteFromCart(item.productId) },
                    )
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", style = MaterialTheme.typography.titleLarge)
                Text("$" + "%.2f".format(total), style = MaterialTheme.typography.titleLarge)
            }
            Button(onClick = onNavigateToCheckoutScreen, modifier = Modifier.fillMaxWidth()) {
                Text("Proceed to Checkout")
            }
        }
    }
}

@Composable
private fun CartRow(item: CartItemUiState, onMinus: () -> Unit, onPlus: () -> Unit, onRemove: () -> Unit) {
    Card {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                item.productImageUrl,
                item.productTitle,
                Modifier.size(64.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
            ) {
                Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                Text("$" + "%.2f".format(item.productPrice))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onMinus) {
                        Text("−")
                    }
                    Text(item.quantity.toString())
                    IconButton(onClick = onPlus) {
                        Text("+")
                    }
                }
            }
            IconButton(onClick = onRemove) {
                Text("Remove", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
