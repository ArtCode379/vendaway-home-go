package vendaway.housewares.vendawayhomego.ui.composable.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import vendaway.housewares.vendawayhomego.ui.state.DataUiState
import vendaway.housewares.vendawayhomego.ui.theme.BrandSuccess
import vendaway.housewares.vendawayhomego.ui.viewmodel.OrderViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val state by viewModel.ordersState.collectAsState()
    val orders = (state as? DataUiState.Populated)?.data.orEmpty().sortedByDescending { it.timestamp }
    if (orders.isEmpty()) {
        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text("No orders yet", modifier = Modifier.padding(24.dp), style = MaterialTheme.typography.titleLarge)
        }
    } else {
        LazyColumn(
            modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text("Reservations", style = MaterialTheme.typography.titleLarge)
            }
            items(orders, key = { it.orderNumber }) { order ->
                Card {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Order #" + order.orderNumber, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Reserved",
                                modifier = Modifier
                                    .background(BrandSuccess.copy(alpha = 0.12f), RoundedCornerShape(50))
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                color = BrandSuccess,
                            )
                        }
                        Text(order.timestamp.toLocalDate().toString())
                        Text(order.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("$" + "%.2f".format(order.price), style = MaterialTheme.typography.titleMedium)
                        Text("Collect within 24 hours", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
