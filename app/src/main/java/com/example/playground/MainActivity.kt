package com.example.playground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playground.ui.theme.PlaygroundTheme

private const val TAG = "Playground"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaygroundTheme {
                UIShowcaseScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIShowcaseScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Android UI Showcase") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            item { TextAndInputSection() }
            item { ButtonsSection() }
            item { SelectionSection() }
            item { IndicatorsAndSlidersSection() }
            item { CardsAndChipsSection() }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

// region Text & Input

@Composable
fun TextAndInputSection() {
    SectionTitle("Text & Input")
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "This is a Text composable",
                style = MaterialTheme.typography.bodyLarge
            )

            var textFieldValue by remember { mutableStateOf("") }
            TextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    Log.d(TAG, "TextField: value changed to \"$it\"")
                },
                label = { Text("TextField") },
                modifier = Modifier.fillMaxWidth()
            )

            var outlinedValue by remember { mutableStateOf("") }
            OutlinedTextField(
                value = outlinedValue,
                onValueChange = {
                    outlinedValue = it
                    Log.d(TAG, "OutlinedTextField: value changed to \"$it\"")
                },
                label = { Text("OutlinedTextField") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// endregion

// region Buttons

@Composable
fun ButtonsSection() {
    SectionTitle("Buttons")
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { Log.d(TAG, "Button: clicked") }) {
                    Text("Button")
                }
                OutlinedButton(onClick = { Log.d(TAG, "OutlinedButton: clicked") }) {
                    Text("Outlined")
                }
                TextButton(onClick = { Log.d(TAG, "TextButton: clicked") }) {
                    Text("Text")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { Log.d(TAG, "IconButton: clicked") }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
                FloatingActionButton(
                    onClick = { Log.d(TAG, "FloatingActionButton: clicked") },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        }
    }
}

// endregion

// region Selection

@Composable
fun SelectionSection() {
    SectionTitle("Selection")
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // Checkbox
            var checked by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        Log.d(TAG, "Checkbox: ${if (it) "checked" else "unchecked"}")
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Checkbox")
            }

            // Switch
            var switchOn by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = switchOn,
                    onCheckedChange = {
                        switchOn = it
                        Log.d(TAG, "Switch: ${if (it) "on" else "off"}")
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Switch")
            }

            // Radio Buttons
            val radioOptions = listOf("Option A", "Option B", "Option C")
            var selectedOption by remember { mutableIntStateOf(0) }
            Text("RadioButton Group", style = MaterialTheme.typography.labelLarge)
            radioOptions.forEachIndexed { index, label ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == index,
                        onClick = {
                            selectedOption = index
                            Log.d(TAG, "RadioButton: selected \"$label\"")
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(label)
                }
            }
        }
    }
}

// endregion

// region Indicators & Sliders

@Composable
fun IndicatorsAndSlidersSection() {
    SectionTitle("Indicators & Sliders")
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            var sliderValue by remember { mutableFloatStateOf(0.5f) }
            Text("Slider: ${"%.2f".format(sliderValue)}")
            Slider(
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                    Log.d(TAG, "Slider: value changed to ${"%.2f".format(it)}")
                }
            )

            Text("LinearProgressIndicator")
            LinearProgressIndicator(
                progress = { sliderValue },
                modifier = Modifier.fillMaxWidth()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("CircularProgressIndicator")
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }
        }
    }
}

// endregion

// region Cards & Chips

@Composable
fun CardsAndChipsSection() {
    SectionTitle("Cards & Chips")
    Card(
        onClick = { Log.d(TAG, "Card: clicked") },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("This is a clickable Card", style = MaterialTheme.typography.bodyLarge)
            Text("Tap anywhere on this card", style = MaterialTheme.typography.bodySmall)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(
                    onClick = { Log.d(TAG, "AssistChip: clicked") },
                    label = { Text("Assist Chip") },
                    leadingIcon = {
                        Icon(Icons.Filled.Star, contentDescription = null, modifier = Modifier.size(18.dp))
                    }
                )

                var filterSelected by remember { mutableStateOf(false) }
                FilterChip(
                    selected = filterSelected,
                    onClick = {
                        filterSelected = !filterSelected
                        Log.d(TAG, "FilterChip: ${if (filterSelected) "selected" else "deselected"}")
                    },
                    label = { Text("Filter Chip") }
                )
            }
        }
    }
}

// endregion

@Preview(showBackground = true)
@Composable
fun UIShowcasePreview() {
    PlaygroundTheme {
        UIShowcaseScreen()
    }
}
