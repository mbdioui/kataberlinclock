# Berlin Clock Kata

Android implementation of the Berlin Clock.

## Tech Stack
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture
- **Testing**: JUnit 5, MockK, Turbine

## How to Run
### Run Application
- **IDE**: Select your device and click the **Run** button in Android Studio.
- **CLI**:
    1. Install: `./gradlew installDebug`
    2. Launch: `adb shell am start -n com.mohamed.kataberlinclock/.MainActivity`

### Run Tests
- **Unit Tests**:
  - **IDE**: Right-click on the `src/test` directory and select **Run 'Tests in...'**.
  - **CLI**: `./gradlew test`
- **UI Tests**:
  - **IDE**: Right-click on the `src/androidTest` directory and select **Run 'Tests in...'**.
  - **CLI**: `./gradlew connectedAndroidTest`

## TDD Approach
- **Red**: Write a failing test for a specific clock rule.
- **Green**: Write the minimal code to make the test pass.
- **Refactor**: Clean up the code while maintaining passing tests.

## Development Approach
- **Domain First**: Strict models (`Time`, `BerlinClock`, `Lamp`) with `init` validation for data integrity.
- **Reactive Flow**: Used `Flow<Time>` for real-time, one-second interval updates.
- **Isolated Logic**: Dedicated mapping logic to convert standard time into Berlin Clock states.
- **Modular UI**: Reusable Compose components (`LampRow`, `LampCapsule`) for a declarative interface.