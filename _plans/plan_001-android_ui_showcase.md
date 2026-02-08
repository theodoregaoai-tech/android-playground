# Plan: Showcase Basic Android UIs

## Context
The current app only shows "Hello Android!" text. The user wants to transform it into a showcase of basic Android UI components, grouped by category, with log output when the user interacts with each component.

## Approach
Update `MainActivity.kt` to contain a scrollable screen with UI components organized into labeled sections using Material 3 cards. Each interaction logs via `android.util.Log` with tag `"Playground"`.

### UI Groups & Components

**1. Text & Input**
- `Text` — static display text
- `TextField` — editable text input (log on value change)
- `OutlinedTextField` — outlined variant (log on value change)

**2. Buttons**
- `Button` — filled button (log on click)
- `OutlinedButton` — outlined variant (log on click)
- `TextButton` — text-only button (log on click)
- `IconButton` — icon button (log on click)
- `FloatingActionButton` — FAB (log on click)

**3. Selection**
- `Checkbox` — toggle checkbox (log on check/uncheck)
- `RadioButton` — radio button group (log on selection)
- `Switch` — toggle switch (log on toggle)

**4. Indicators & Sliders**
- `Slider` — value slider (log on value change)
- `LinearProgressIndicator` — progress bar (tied to slider value)
- `CircularProgressIndicator` — spinner

**5. Cards & Chips**
- `Card` — clickable card (log on click)
- `AssistChip` / `FilterChip` — chip components (log on click/select)

## Files to Modify
- **`app/src/main/java/com/example/playground/MainActivity.kt`** — replace current content with the UI showcase

No new files needed. All composables will live in `MainActivity.kt` to keep it simple — one file, one scrollable screen.

## Implementation Details
- Use `LazyColumn` for the scrollable list
- Each group wrapped in a section composable with a title `Text` and a `Card` containing the components
- Use `remember` / `mutableStateOf` for local state (text fields, checkbox, switch, slider, etc.)
- Log format: `Log.d("Playground", "ComponentName: action details")`
- Keep existing `PlaygroundTheme` and `Scaffold` wrapper
- Remove the old `Greeting` composable

## Verification
1. `./gradlew installDebug` — build and deploy
2. `adb shell am start -n com.example.playground/.MainActivity` — launch
3. Interact with each component and verify logs via `adb logcat -s Playground`
