# FinansDostum Mobile App Testing

## Overview
This project implements Model-Based Testing (MBT) for the FinansDostum iOS mobile application using GraphWalker. The tests automatically verify the core functionality of adding income entries to the finance tracking app using a state machine model approach.

## Project Structure
- `src/main/java/`
  - `models/` - GraphWalker models (e.g., AddIncome.java)
  - `pages/` - Page objects for the application (e.g., HomePage.java)
  - `utils/` - Utility classes for test execution
    - `CoverageValue.java` - Constants for GraphWalker coverage configurations
    - `IOSBaseTestClass.java` - Base class for iOS test execution
    - `TestLogger.java` - Custom logging implementation
    - `TestResultHandler.java` - JUnit test result handler
  - `webdriver/` - Appium configuration and WebDriver helpers

## Prerequisites
- Java 11
- Maven
- Appium 2.x
- iOS Simulator or real device with Developer mode enabled
- XCode (for iOS app development and simulator)
- GraphWalker Studio 4.3.3 (for model visualization and debugging)

## Installation
1. Clone the repository
```
git clone https://github.com/yourusername/FinansDostum-MBT.git
cd FinansDostum-MBT
```

2. Install dependencies
```
mvn clean install
```

3. Start the Appium server (in a separate terminal)
```
appium
```

## Configuration
1. Update the device details in `IOSBaseTestClass.java` if needed
2. Check the `DriverSetup.java` class to ensure correct Appium capabilities

## Running Tests
Execute the test with:
```
mvn test -Dtest=RunAddIncomeModel
```

Alternatively, run a specific test class directly:
```
java -jar graphwalker-studio-4.3.3.jar
```
Then open the GraphWalker Studio UI in your browser and load the model.

## Model Description
The application testing uses a state machine model with the following components:

### States (Vertices)
- `v_Home` - Home screen of the application
- `v_IncomeSheetOpened` - Add income sheet is open
- `v_ExpenseSheetOpened` - Add expense sheet is open
- `v_IncomeAmountFieldFilled` - Income amount field has been filled
- `v_CleanedAmountFielde` - Amount field has been cleared
- `v_IncomeTitleWritten` - Income title has been entered
- `v_TagSelected` - Income category tag has been selected
- `v_NoteAdded` - Note has been added to the income entry
- `v_IncomeSaved` - Income entry has been successfully saved

### Transitions (Edges)
- `e_ClickAddButton` - Click the add button
- `e_SwitchToIncome` - Switch to income view
- `e_SwitchToExpense` - Switch to expense view
- `e_FillIncomeAmountField` - Fill in the income amount
- `e_CleanIncomeAmount` - Clear the amount field
- `e_TypeIncomeTitle` - Enter income title
- `e_SelectIncomeType` - Select income category
- `e_AddNote` - Add a note to the income
- `e_SaveIncome` - Save the income entry
- `e_CancelIncome` - Cancel income creation
- `e_SystemRedirect` - System redirects back to home

## Logging
The project includes a custom logging system that creates detailed logs for each test run. Logs are saved in the `test-logs` directory with timestamps in the filename format.

## Coverage Configuration
The project uses the following GraphWalker coverage configurations:
- `RandomEdgeCoverage100` - Random path traversal with 100% edge coverage
- `QuickRandomEdgeCoverage100` - Quicker random path with 100% edge coverage
- `WeightedRandomEdgeCoverage100` - Weighted random path with 100% edge coverage
- `WeightedRandomEdgeCoverage60` - Weighted random path with 60% edge coverage

## WebSocket Server
The test execution starts a WebSocket server on port 8890, allowing you to connect GraphWalker Studio to visualize the test execution in real-time.
