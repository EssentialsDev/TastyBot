package dev.essentials.tastybot.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.essentials.tastybot.data.Strategy
import dev.essentials.tastybot.repository.StrategyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StrategyViewModel(private val repository: StrategyRepository) : ViewModel() {

    val selectedStrategy: StateFlow<Strategy> = repository.selectedStrategy.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        Strategy.PREMIUM_SELLING
    )

    val strategyMetrics = repository.strategyMetrics.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )

    fun selectStrategy(strategy: Strategy) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateStrategy(strategy)
    }
}