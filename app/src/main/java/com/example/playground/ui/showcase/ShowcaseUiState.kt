package com.example.playground.ui.showcase

data class ShowcaseUiState(
    val textFieldValue: String = "",
    val outlinedTextFieldValue: String = "",
    val isChecked: Boolean = false,
    val isSwitchOn: Boolean = false,
    val selectedRadioOption: Int = 0,
    val sliderValue: Float = 0.5f,
    val isFilterChipSelected: Boolean = false,
)
