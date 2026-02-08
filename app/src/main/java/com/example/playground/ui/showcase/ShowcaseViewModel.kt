package com.example.playground.ui.showcase

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "Playground"

class ShowcaseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ShowcaseUiState())
    val uiState: StateFlow<ShowcaseUiState> = _uiState.asStateFlow()

    fun onTextFieldValueChange(value: String) {
        _uiState.update { it.copy(textFieldValue = value) }
        Log.d(TAG, "TextField: value changed to \"$value\"")
    }

    fun onOutlinedTextFieldValueChange(value: String) {
        _uiState.update { it.copy(outlinedTextFieldValue = value) }
        Log.d(TAG, "OutlinedTextField: value changed to \"$value\"")
    }

    fun onCheckedChange(checked: Boolean) {
        _uiState.update { it.copy(isChecked = checked) }
        Log.d(TAG, "Checkbox: ${if (checked) "checked" else "unchecked"}")
    }

    fun onSwitchChange(on: Boolean) {
        _uiState.update { it.copy(isSwitchOn = on) }
        Log.d(TAG, "Switch: ${if (on) "on" else "off"}")
    }

    fun onRadioOptionSelected(index: Int) {
        _uiState.update { it.copy(selectedRadioOption = index) }
        Log.d(TAG, "RadioButton: selected option $index")
    }

    fun onSliderValueChange(value: Float) {
        _uiState.update { it.copy(sliderValue = value) }
        Log.d(TAG, "Slider: value changed to ${"%.2f".format(value)}")
    }

    fun onFilterChipToggle() {
        _uiState.update { it.copy(isFilterChipSelected = !it.isFilterChipSelected) }
        Log.d(TAG, "FilterChip: ${if (_uiState.value.isFilterChipSelected) "selected" else "deselected"}")
    }

    fun onButtonClick(label: String) {
        Log.d(TAG, "$label: clicked")
    }
}
