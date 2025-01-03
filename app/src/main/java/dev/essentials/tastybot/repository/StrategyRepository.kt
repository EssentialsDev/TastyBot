package dev.essentials.tastybot.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.essentials.tastybot.data.Metric
import dev.essentials.tastybot.data.MetricData
import dev.essentials.tastybot.data.Strategy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StrategyRepository(private val dataStore: DataStore<Preferences>) {

    private val _selectedStrategyKey = stringPreferencesKey("selected_strategy")

    val selectedStrategy = dataStore.data
        .map { preferences ->
            val strategyName = preferences[_selectedStrategyKey] ?: Strategy.PREMIUM_SELLING.name
            Strategy.valueOf(strategyName)
        }

    suspend fun updateStrategy(strategy: Strategy) {
        dataStore.edit { preferences ->
            preferences[_selectedStrategyKey] = strategy.name
        }
    }

    val strategyMetrics: Flow<List<MetricData>> = selectedStrategy.map { strategy ->
        when (strategy) {
            Strategy.PREMIUM_SELLING -> getPremiumSellingMetrics()
            Strategy.NEUTRAL -> getNeutralMetrics()
            Strategy.DIRECTIONAL -> getDirectionalMetrics()
            Strategy.HIGH_IV -> getHighIvMetrics()
        }
    }

    private fun getPremiumSellingMetrics(): List<MetricData> {
        return listOf(
            MetricData(Metric.THETA, 40.0),
            MetricData(Metric.DELTA, 0.25),
            MetricData(Metric.POP, 70.0)
        )
    }

    private fun getNeutralMetrics(): List<MetricData> {
        return listOf(
            MetricData(Metric.THETA, 30.0),
            MetricData(Metric.VEGA, 15.0),
            MetricData(Metric.DELTA, 0.1)
        )
    }

    private fun getDirectionalMetrics(): List<MetricData> {
        return listOf(
            MetricData(Metric.DELTA, 0.8),
            MetricData(Metric.GAMMA, 0.05),
            MetricData(Metric.THETA, -5.0)
        )
    }

    private fun getHighIvMetrics(): List<MetricData> {
        return listOf(
            MetricData(Metric.VEGA, 25.0),
            MetricData(Metric.POP, 60.0),
            MetricData(Metric.THETA, 50.0)
        )
    }
}