# MVVM Refactoring Plan

## Context
The current project is a single-file UI showcase app with all composables and state in `MainActivity.kt`. All state is managed locally via `remember`/`mutableStateOf`. There are no ViewModels, UI state classes, or structured event handling. The goal is to refactor to MVVM pattern: extract state into a ViewModel with `StateFlow`, create a UI state data class, and organize files by feature.

## Changes

### 1. Add `lifecycle-viewmodel-compose` dependency
- **File**: `gradle/libs.versions.toml` — add `lifecycleViewmodelCompose` version and library entry
- **File**: `app/build.gradle.kts` — add `implementation(libs.androidx.lifecycle.viewmodel.compose)`

### 2. Create package structure
```
com.example.playground/
├── MainActivity.kt              (simplified — just launches ShowcaseScreen)
└── ui/
    ├── theme/                    (unchanged)
    └── showcase/
        ├── ShowcaseUiState.kt    (data class holding all UI state)
        ├── ShowcaseViewModel.kt  (ViewModel with StateFlow + event handlers)
        └── ShowcaseScreen.kt     (all composables — screen + sections)
```

### 3. Create `ShowcaseUiState.kt`
- **Path**: `app/src/main/java/com/example/playground/ui/showcase/ShowcaseUiState.kt`
- Data class with fields:
  - `textFieldValue: String = ""`
  - `outlinedTextFieldValue: String = ""`
  - `isChecked: Boolean = false`
  - `isSwitchOn: Boolean = false`
  - `selectedRadioOption: Int = 0`
  - `sliderValue: Float = 0.5f`
  - `isFilterChipSelected: Boolean = false`

### 4. Create `ShowcaseViewModel.kt`
- **Path**: `app/src/main/java/com/example/playground/ui/showcase/ShowcaseViewModel.kt`
- Extends `ViewModel()`
- Private `_uiState = MutableStateFlow(ShowcaseUiState())`
- Public `uiState: StateFlow<ShowcaseUiState> = _uiState.asStateFlow()`
- Event handler methods that update `_uiState` via `.update {}` and log with `Log.d`:
  - `onTextFieldValueChange(value: String)`
  - `onOutlinedTextFieldValueChange(value: String)`
  - `onCheckedChange(checked: Boolean)`
  - `onSwitchChange(on: Boolean)`
  - `onRadioOptionSelected(index: Int)`
  - `onSliderValueChange(value: Float)`
  - `onFilterChipToggle()`
  - `onButtonClick(label: String)` — for all stateless button/card/chip clicks (just logs)

### 5. Create `ShowcaseScreen.kt`
- **Path**: `app/src/main/java/com/example/playground/ui/showcase/ShowcaseScreen.kt`
- Move all composables from `MainActivity.kt`
- Top-level `ShowcaseScreen(viewModel: ShowcaseViewModel = viewModel())` that collects state via `collectAsStateWithLifecycle()`
- Section composables receive state values and event callbacks (state hoisting)
- Preview composable uses default `ShowcaseUiState()`

### 6. Simplify `MainActivity.kt`
- **Path**: `app/src/main/java/com/example/playground/MainActivity.kt`
- Only `setContent { PlaygroundTheme { ShowcaseScreen() } }`
- Remove all composable functions and imports

## Files Modified
1. `gradle/libs.versions.toml` — add viewmodel-compose dependency
2. `app/build.gradle.kts` — add viewmodel-compose implementation
3. `app/src/main/java/com/example/playground/MainActivity.kt` — simplify
4. `app/src/main/java/com/example/playground/ui/showcase/ShowcaseUiState.kt` — new
5. `app/src/main/java/com/example/playground/ui/showcase/ShowcaseViewModel.kt` — new
6. `app/src/main/java/com/example/playground/ui/showcase/ShowcaseScreen.kt` — new

## Verification
```bash
./gradlew assembleDebug   # Verify build succeeds
./gradlew test            # Verify existing tests pass
```
Then install on device/emulator and verify all UI interactions work as before.
