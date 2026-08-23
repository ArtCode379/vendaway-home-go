package vendaway.housewares.vendawayhomego.ui.composable.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.LocalShipping
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import vendaway.housewares.vendawayhomego.ui.viewmodel.OJFXTOnboardingVM

private data class Page(val title: String, val body: String, val icon: ImageVector)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OJFXTOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pages = listOf(
        Page("Everything for your space", "Discover useful homewares, smart electronics, and seasonal favorites.", Icons.Rounded.ShoppingBag),
        Page("Choose with confidence", "Clear details and curated picks make every choice easier.", Icons.Rounded.AutoAwesome),
        Page("Reserve and collect", "We will hold your confirmed order in store for 24 hours.", Icons.Rounded.LocalShipping),
    )
    val pager = rememberPagerState(pageCount = { pages.size })
    LaunchedEffect(completed) {
        if (completed) onNavigateToHomeScreen()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(state = pager, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(128.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(32.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(page.icon, null, Modifier.size(64.dp), MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.height(32.dp))
                Text(page.title, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
                Spacer(Modifier.height(12.dp))
                Text(page.body, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Box(
                    Modifier
                        .size(if (index == pager.currentPage) 22.dp else 8.dp, 8.dp)
                        .background(
                            if (index == pager.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                            CircleShape,
                        ),
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        if (pager.currentPage == pages.lastIndex) {
            Button(onClick = viewModel::setOnboarded, modifier = Modifier.fillMaxWidth()) {
                Text("Get Started")
            }
        } else {
            Text("Swipe to continue", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
