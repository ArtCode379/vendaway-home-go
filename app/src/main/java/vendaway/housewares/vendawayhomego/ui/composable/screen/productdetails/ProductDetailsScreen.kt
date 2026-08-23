package vendaway.housewares.vendawayhomego.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import vendaway.housewares.vendawayhomego.ui.state.DataUiState
import vendaway.housewares.vendawayhomego.ui.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.productDetailsState.collectAsState()
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(productId) {
        viewModel.observeProductDetails(productId)
    }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    val product = (state as? DataUiState.Populated)?.data
    Box(modifier.fillMaxSize()) {
        if (product != null) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                AsyncImage(
                    product.imageUrl,
                    product.title,
                    Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                )
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(product.title, style = MaterialTheme.typography.titleLarge)
                    Text(
                        "$" + "%.2f".format(product.price),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        product.category.name.lowercase().replaceFirstChar { it.uppercase() },
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                    )
                    Text(product.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = {
                            viewModel.addProductToCart()
                            cartAdded = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Add to Cart")
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                "✓ Added to cart",
                Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}
