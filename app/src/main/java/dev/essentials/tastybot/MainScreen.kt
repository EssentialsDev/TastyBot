package dev.essentials.tastybot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.essentials.tastybot.ui.components.MetricsGrid
import dev.essentials.tastybot.ui.components.StrategyTopBar
import dev.essentials.tastybot.viewmodels.StrategyViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val viewModel: StrategyViewModel = koinViewModel()

    val selectedStrategy by viewModel.selectedStrategy.collectAsState()
    val strategyMetrics by viewModel.strategyMetrics.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        StrategyTopBar(
            selectedStrategy = selectedStrategy,
            onStrategySelected = { newStrategy ->
                viewModel.selectStrategy(newStrategy)
            },
        )
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MetricsGrid(strategyMetrics)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}