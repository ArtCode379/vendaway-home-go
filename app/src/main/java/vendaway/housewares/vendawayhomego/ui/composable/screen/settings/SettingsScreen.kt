package vendaway.housewares.vendawayhomego.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val openWebsite = {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://vendaway.surf")))
    }
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)
        Card {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("About", style = MaterialTheme.typography.titleMedium)
                Text("Vendaway Home Go")
                Text("VENDAWAY LTD")
                Text("Version 1.0", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Card {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Legal & support", style = MaterialTheme.typography.titleMedium)
                Text("Product reservations are held in store for 24 hours.")
                Button(onClick = openWebsite, modifier = Modifier.fillMaxWidth()) {
                    Text("Customer Support")
                }
            }
        }
    }
}
