# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Android application built with Kotlin, Jetpack Compose, and Material 3. Uses Gradle with Kotlin DSL and a version catalog for dependency management.

- **Package**: `com.example.playground`
- **Min SDK**: 24 (Android 7.0)
- **Target/Compile SDK**: 35
- **Kotlin**: 2.0.21 with Compose compiler plugin
- **JVM Target**: 11

## Build Commands

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew assembleRelease        # Build release APK
./gradlew installDebug           # Build and install on connected device/emulator
./gradlew test                   # Run unit tests
./gradlew connectedAndroidTest   # Run instrumented tests (requires device/emulator)
./gradlew app:testDebugUnitTest --tests "com.example.playground.ExampleUnitTest"  # Run a single test class
./gradlew clean                  # Clean build outputs
```

Requires JDK 17+ (Android Studio bundles one). Android SDK is expected at `~/Library/Android/sdk` or via `ANDROID_HOME`.

## Architecture

Single-module project (`app`). Dependencies are managed via the version catalog at `gradle/libs.versions.toml`.

- **Entry point**: `MainActivity` uses `setContent` with Compose
- **Theme**: `ui/theme/` — Material 3 with dynamic color support (Android 12+), falling back to a purple color scheme
- **Source root**: `app/src/main/java/com/example/playground/`
- **Unit tests**: `app/src/test/` (JUnit 4)
- **Instrumented tests**: `app/src/androidTest/` (AndroidJUnit4 + Espresso)
