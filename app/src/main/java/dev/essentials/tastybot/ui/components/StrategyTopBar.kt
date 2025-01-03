package dev.essentials.tastybot.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.essentials.tastybot.data.Strategy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StrategyTopBar(
    selectedStrategy: Strategy,
    onStrategySelected: (Strategy) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            TextButton(
                onClick = { expanded = !expanded },
            ) {
                Text(
                    text = selectedStrategy.name.replace("_", " "),
                    style = MaterialTheme.typography.titleLarge
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Strategy",
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Strategy.entries.forEach { strategy ->
                    DropdownMenuItem(
                        onClick = {
                            onStrategySelected(strategy)
                            expanded = false
                        },
                        text = {
                            Text(text = strategy.name.replace("_", " "))
                        }
                    )
                }
            }
        },
    )
}

@Preview
@Composable
fun StrategyTopBarPreview() {
    StrategyTopBar(Strategy.PREMIUM_SELLING) {}
}
