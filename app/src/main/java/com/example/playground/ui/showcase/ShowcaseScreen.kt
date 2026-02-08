package com.example.playground.ui.showcase

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playground.ui.theme.PlaygroundTheme

@Composable
fun ShowcaseScreen(viewModel: ShowcaseViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ShowcaseScreenContent(
        uiState = uiState,
        onTextFieldValueChange = viewModel::onTextFieldValueChange,
        onOutlinedTextFieldValueChange = viewModel::onOutlinedTextFieldValueChange,
        onCheckedChange = viewModel::onCheckedChange,
        onSwitchChange = viewModel::onSwitchChange,
        onRadioOptionSelected = viewModel::onRadioOptionSelected,
        onSliderValueChange = viewModel::onSliderValueChange,
        onFilterChipToggle = viewModel::onFilterChipToggle,
        onButtonClick = viewModel::onButtonClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowcaseScreenContent(
    uiState: ShowcaseUiState,
    onTextFieldValueChange: (String) -> Unit = {},
    onOutlinedTextFieldValueChange: (String) -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
    onSwitchChange: (Boolean) -> Unit = {},
    onRadioOptionSelected: (Int) -> Unit = {},
    onSliderValueChange: (Float) -> Unit = {},
    onFilterChipToggle: () -> Unit = {},
    onButtonClick: (String) -> Unit = {},
) {
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
            item {
                TextAndInputSection(
                    textFieldValue = uiState.textFieldValue,
                    onTextFieldValueChange = onTextFieldValueChange,
                    outlinedTextFieldValue = uiState.outlinedTextFieldValue,
                    onOutlinedTextFieldValueChange = onOutlinedTextFieldValueChange,
                )
            }
            item {
                ButtonsSection(onButtonClick = onButtonClick)
            }
            item {
                SelectionSection(
                    isChecked = uiState.isChecked,
                    onCheckedChange = onCheckedChange,
                    isSwitchOn = uiState.isSwitchOn,
                    onSwitchChange = onSwitchChange,
                    selectedRadioOption = uiState.selectedRadioOption,
                    onRadioOptionSelected = onRadioOptionSelected,
                )
            }
            item {
                IndicatorsAndSlidersSection(
                    sliderValue = uiState.sliderValue,
                    onSliderValueChange = onSliderValueChange,
                )
            }
            item {
                CardsAndChipsSection(
                    isFilterChipSelected = uiState.isFilterChipSelected,
                    onFilterChipToggle = onFilterChipToggle,
                    onButtonClick = onButtonClick,
                )
            }
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

@Composable
fun TextAndInputSection(
    textFieldValue: String,
    onTextFieldValueChange: (String) -> Unit,
    outlinedTextFieldValue: String,
    onOutlinedTextFieldValueChange: (String) -> Unit,
) {
    SectionTitle("Text & Input")
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "This is a Text composable",
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                value = textFieldValue,
                onValueChange = onTextFieldValueChange,
                label = { Text("TextField") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = outlinedTextFieldValue,
                onValueChange = onOutlinedTextFieldValueChange,
                label = { Text("OutlinedTextField") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ButtonsSection(onButtonClick: (String) -> Unit) {
    SectionTitle("Buttons")
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { onButtonClick("Button") }) {
                    Text("Button")
                }
                OutlinedButton(onClick = { onButtonClick("OutlinedButton") }) {
                    Text("Outlined")
                }
                TextButton(onClick = { onButtonClick("TextButton") }) {
                    Text("Text")
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onButtonClick("IconButton") }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
                FloatingActionButton(
                    onClick = { onButtonClick("FloatingActionButton") },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        }
    }
}

@Composable
fun SelectionSection(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isSwitchOn: Boolean,
    onSwitchChange: (Boolean) -> Unit,
    selectedRadioOption: Int,
    onRadioOptionSelected: (Int) -> Unit,
) {
    SectionTitle("Selection")
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Checkbox")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(checked = isSwitchOn, onCheckedChange = onSwitchChange)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Switch")
            }
            val radioOptions = listOf("Option A", "Option B", "Option C")
            Text("RadioButton Group", style = MaterialTheme.typography.labelLarge)
            radioOptions.forEachIndexed { index, label ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedRadioOption == index,
                        onClick = { onRadioOptionSelected(index) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(label)
                }
            }
        }
    }
}

@Composable
fun IndicatorsAndSlidersSection(
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit,
) {
    SectionTitle("Indicators & Sliders")
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Slider: ${"%.2f".format(sliderValue)}")
            Slider(value = sliderValue, onValueChange = onSliderValueChange)
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

@Composable
fun CardsAndChipsSection(
    isFilterChipSelected: Boolean,
    onFilterChipToggle: () -> Unit,
    onButtonClick: (String) -> Unit,
) {
    SectionTitle("Cards & Chips")
    Card(
        onClick = { onButtonClick("Card") },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("This is a clickable Card", style = MaterialTheme.typography.bodyLarge)
            Text("Tap anywhere on this card", style = MaterialTheme.typography.bodySmall)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(
                    onClick = { onButtonClick("AssistChip") },
                    label = { Text("Assist Chip") },
                    leadingIcon = {
                        Icon(Icons.Filled.Star, contentDescription = null, modifier = Modifier.size(18.dp))
                    }
                )
                FilterChip(
                    selected = isFilterChipSelected,
                    onClick = onFilterChipToggle,
                    label = { Text("Filter Chip") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowcaseScreenPreview() {
    PlaygroundTheme {
        ShowcaseScreenContent(uiState = ShowcaseUiState())
    }
}
