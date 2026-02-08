package com.example.playground.ui.showcase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShowcaseViewModelTest {

    private lateinit var viewModel: ShowcaseViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ShowcaseViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state has default values`() {
        val state = viewModel.uiState.value
        assertEquals("", state.textFieldValue)
        assertEquals("", state.outlinedTextFieldValue)
        assertFalse(state.isChecked)
        assertFalse(state.isSwitchOn)
        assertEquals(0, state.selectedRadioOption)
        assertEquals(0.5f, state.sliderValue)
        assertFalse(state.isFilterChipSelected)
    }

    @Test
    fun `onTextFieldValueChange updates textFieldValue`() {
        viewModel.onTextFieldValueChange("hello")
        assertEquals("hello", viewModel.uiState.value.textFieldValue)
    }

    @Test
    fun `onTextFieldValueChange updates to empty string`() {
        viewModel.onTextFieldValueChange("hello")
        viewModel.onTextFieldValueChange("")
        assertEquals("", viewModel.uiState.value.textFieldValue)
    }

    @Test
    fun `onOutlinedTextFieldValueChange updates outlinedTextFieldValue`() {
        viewModel.onOutlinedTextFieldValueChange("world")
        assertEquals("world", viewModel.uiState.value.outlinedTextFieldValue)
    }

    @Test
    fun `onOutlinedTextFieldValueChange updates to empty string`() {
        viewModel.onOutlinedTextFieldValueChange("world")
        viewModel.onOutlinedTextFieldValueChange("")
        assertEquals("", viewModel.uiState.value.outlinedTextFieldValue)
    }

    @Test
    fun `onCheckedChange sets isChecked to true`() {
        viewModel.onCheckedChange(true)
        assertTrue(viewModel.uiState.value.isChecked)
    }

    @Test
    fun `onCheckedChange sets isChecked to false`() {
        viewModel.onCheckedChange(true)
        viewModel.onCheckedChange(false)
        assertFalse(viewModel.uiState.value.isChecked)
    }

    @Test
    fun `onSwitchChange sets isSwitchOn to true`() {
        viewModel.onSwitchChange(true)
        assertTrue(viewModel.uiState.value.isSwitchOn)
    }

    @Test
    fun `onSwitchChange sets isSwitchOn to false`() {
        viewModel.onSwitchChange(true)
        viewModel.onSwitchChange(false)
        assertFalse(viewModel.uiState.value.isSwitchOn)
    }

    @Test
    fun `onRadioOptionSelected updates selectedRadioOption`() {
        viewModel.onRadioOptionSelected(1)
        assertEquals(1, viewModel.uiState.value.selectedRadioOption)
    }

    @Test
    fun `onRadioOptionSelected can switch between options`() {
        viewModel.onRadioOptionSelected(2)
        assertEquals(2, viewModel.uiState.value.selectedRadioOption)
        viewModel.onRadioOptionSelected(0)
        assertEquals(0, viewModel.uiState.value.selectedRadioOption)
    }

    @Test
    fun `onSliderValueChange updates sliderValue`() {
        viewModel.onSliderValueChange(0.75f)
        assertEquals(0.75f, viewModel.uiState.value.sliderValue)
    }

    @Test
    fun `onSliderValueChange updates to boundary values`() {
        viewModel.onSliderValueChange(0.0f)
        assertEquals(0.0f, viewModel.uiState.value.sliderValue)
        viewModel.onSliderValueChange(1.0f)
        assertEquals(1.0f, viewModel.uiState.value.sliderValue)
    }

    @Test
    fun `onFilterChipToggle toggles isFilterChipSelected`() {
        assertFalse(viewModel.uiState.value.isFilterChipSelected)
        viewModel.onFilterChipToggle()
        assertTrue(viewModel.uiState.value.isFilterChipSelected)
        viewModel.onFilterChipToggle()
        assertFalse(viewModel.uiState.value.isFilterChipSelected)
    }

    @Test
    fun `onButtonClick does not change state`() {
        val stateBefore = viewModel.uiState.value
        viewModel.onButtonClick("Button")
        assertEquals(stateBefore, viewModel.uiState.value)
    }

    @Test
    fun `multiple state changes are independent`() {
        viewModel.onTextFieldValueChange("text")
        viewModel.onCheckedChange(true)
        viewModel.onSliderValueChange(0.8f)

        val state = viewModel.uiState.value
        assertEquals("text", state.textFieldValue)
        assertTrue(state.isChecked)
        assertEquals(0.8f, state.sliderValue)
        // Other values remain default
        assertEquals("", state.outlinedTextFieldValue)
        assertFalse(state.isSwitchOn)
        assertEquals(0, state.selectedRadioOption)
        assertFalse(state.isFilterChipSelected)
    }
}
