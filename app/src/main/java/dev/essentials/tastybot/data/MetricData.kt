package dev.essentials.tastybot.data

enum class Metric {
    THETA, GAMMA, DELTA, VEGA, POP, RHO, IVR, IV, P50, MAX_PROFIT, MAX_LOSS, BREAK_EVEN
}

data class MetricData(
    val metric: Metric,
    val value: Double
)

fun formatMetricValue(metric: Metric, value: Double): String {
    return when (metric) {
        Metric.POP, Metric.P50 -> "${value}%"
        Metric.MAX_PROFIT, Metric.MAX_LOSS -> "$${value.toInt()}"
        else -> value.toString()
    }
}