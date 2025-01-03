package dev.essentials.tastybot.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.essentials.tastybot.data.Metric
import dev.essentials.tastybot.data.MetricData
import dev.essentials.tastybot.data.formatMetricValue

@Composable
fun MetricsGrid(metrics: List<MetricData>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(metrics) { data ->
            MetricCard(
                metricData = data,
            )
        }
    }
}

@Composable
fun MetricCard(
    metricData: MetricData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = metricData.metric.name.replace("_", " "),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formatMetricValue(metricData.metric, metricData.value),
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}

@Preview
@Composable
fun MetricCardPreview() {
    val metrics = listOf(
        MetricData(metric = Metric.THETA, value = 40.0),
        MetricData(metric = Metric.DELTA, value = 0.25),
        MetricData(metric = Metric.POP, value = 70.5),
        MetricData(metric = Metric.GAMMA, value = 0.1),
        MetricData(metric = Metric.VEGA, value = 12.5),
        MetricData(metric = Metric.IVR, value = 45.0)
    )
    MetricsGrid(
        metrics = metrics
    )
}
