package vendaway.housewares.vendawayhomego.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import vendaway.housewares.vendawayhomego.data.model.Product
import vendaway.housewares.vendawayhomego.data.model.ProductCategory
import vendaway.housewares.vendawayhomego.ui.state.DataUiState
import vendaway.housewares.vendawayhomego.ui.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    var selected by remember { mutableStateOf<ProductCategory?>(null) }
    val products = (state as? DataUiState.Populated)?.data.orEmpty()
    val shown = selected?.let { category -> products.filter { it.category == category } } ?: products
    Column(modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text("Vendaway Home Go", style = MaterialTheme.typography.titleLarge)
                Text("Useful finds for everyday living", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = { selected = null }) {
                Icon(Icons.Rounded.Search, "Show all products")
            }
        }
        if (products.isNotEmpty()) {
            val pager = rememberPagerState(pageCount = { minOf(4, products.size) })
            HorizontalPager(
                state = pager,
                contentPadding = PaddingValues(horizontal = 20.dp),
                pageSpacing = 12.dp,
            ) { page ->
                val item = products[page]
                Card(
                    Modifier
                        .height(180.dp)
                        .clickable { onNavigateToProductDetails(item.id) },
                    shape = RoundedCornerShape(20.dp),
                ) {
                    AsyncImage(item.imageUrl, item.title, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                }
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                AssistChip(onClick = { selected = null }, label = { Text("All") })
            }
            items(ProductCategory.entries.size) { index ->
                val category = ProductCategory.entries[index]
                AssistChip(
                    onClick = { selected = category },
                    label = { Text(stringResource(category.titleRes)) },
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(shown, key = { it.id }) { item ->
                ProductCard(item) { onNavigateToProductDetails(item.id) }
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(Modifier.clickable(onClick = onClick), shape = RoundedCornerShape(16.dp)) {
        AsyncImage(
            product.imageUrl,
            product.title,
            Modifier
                .fillMaxWidth()
                .height(132.dp),
            contentScale = ContentScale.Crop,
        )
        Column(Modifier.padding(12.dp)) {
            Text(product.title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
            Text(stringResource(product.category.titleRes), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("$" + "%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }
    }
}
