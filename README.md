# Java Quiz Game - OOP Project

![Quiz Game Screenshot](/Screenshot%202026-05-05%20224613.png)

A comprehensive Java Swing-based quiz game application focused on Java programming fundamentals. This project demonstrates key Object-Oriented Programming (OOP) concepts including encapsulation, inheritance, polymorphism, and GUI development.

## Project Overview

The Java Quiz Game is an interactive educational application that tests users' knowledge of Java programming fundamentals through multiple-choice questions. The application features a user-friendly GUI built with Java Swing and provides immediate feedback with detailed explanations for each answer.

## Features

- **Interactive GUI**: Built with Java Swing for a professional desktop experience
- **10 Quiz Questions**: Comprehensive questions covering Java fundamentals
- **Multiple-Choice Format**: 4 options per question with immediate feedback
- **Score Tracking**: Real-time score display and percentage calculation
- **Detailed Explanations**: Each question includes an explanation of the correct answer
- **Question Navigation**: Move forward and backward through questions
- **Quiz Results**: Summary with performance percentage and personalized feedback
- **Color-Coded Feedback**: Visual indicators for correct (green) and incorrect (red) answers

## Topics Covered

The quiz covers the following Java programming fundamentals:
- Variable Declaration and Data Types
- Primitive vs Reference Types
- Default Values and Initialization
- Object Creation with `new` Keyword
- Object-Oriented Programming Concepts
- Access Modifiers (private, protected, public)
- Inheritance and Extends Keyword
- Runnable Interface and Threading
- Polymorphism
- Abstract Classes

## Project Structure

```
Quiz_Game/
├── src/
│   ├── QuizGame.java          # Main GUI application class
│   ├── QuizQuestion.java      # Model class for quiz questions
│   └── QuizData.java          # Quiz questions database
├── compile.bat                # Windows compile script
├── run.bat                    # Windows run script
├── compile.sh                 # Linux/Mac compile script
├── run.sh                     # Linux/Mac run script
└── README.md                  # This file
```

## File Descriptions

### QuizGame.java
Main application class that creates and manages the GUI. Implements:
- JFrame for the main window
- JPanel for layout management
- JRadioButton for answer selection
- ActionListeners for button events
- Score tracking and navigation logic

### QuizQuestion.java
Model class representing a single quiz question:
- Stores question text, options, correct answer index
- Provides explanation for answers
- Includes `isCorrect()` method for answer validation

### QuizData.java
Utility class containing all quiz questions:
- Static method `getQuestions()` returns array of QuizQuestion objects
- Centralized location for all quiz content
- Easy to extend with more questions

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Basic understanding of Java and command-line operations

## Getting Started

### Method 1: Using Batch Scripts (Windows)

1. Open Command Prompt and navigate to the project directory:
   ```bash
   cd "C:\Users\CHRITOPHER\Documents\Open Source Project\Quiz_Game"
   ```

2. Compile the application:
   ```bash
   compile.bat
   ```

3. Run the application:
   ```bash
   run.bat
   ```

### Method 2: Using Shell Scripts (Linux/Mac)

1. Open Terminal and navigate to the project directory:
   ```bash
   cd ~/Documents/Open\ Source\ Project/Quiz_Game
   ```

2. Make scripts executable:
   ```bash
   chmod +x compile.sh run.sh
   ```

3. Compile the application:
   ```bash
   ./compile.sh
   ```

4. Run the application:
   ```bash
   ./run.sh
   ```

### Method 3: Manual Compilation and Execution

1. Navigate to the src directory:
   ```bash
   cd src
   ```

2. Compile all Java files:
   ```bash
   javac QuizQuestion.java QuizData.java QuizGame.java
   ```

3. Run the application:
   ```bash
   java QuizGame
   ```

## How to Use

1. **Start the Application**: Launch the quiz game using one of the methods above
2. **Read the Question**: Each question is displayed at the top of the window
3. **Select an Answer**: Click on one of the four radio buttons to select your answer
4. **Submit Answer**: Click the "Next" button to submit your answer and move to the next question
5. **Review Feedback**: After selecting an answer, you'll see:
   - Visual indication (green for correct, red for incorrect)
   - The correct answer if you were wrong
   - A detailed explanation of the topic
6. **Navigate**: Use "Previous" button to go back to earlier questions (if needed)
7. **View Results**: After the last question, see your final score and performance feedback

## Scoring

- **Correct Answer**: +1 point
- **Incorrect Answer**: No points awarded
- **Final Score**: Displayed as "X out of 10"
- **Percentage**: Calculated as (Score × 100) ÷ 10
- **Feedback Tiers**:
  - 80-100%: "Excellent! You have a strong understanding of Java fundamentals!"
  - 60-79%: "Good job! Review the weak areas to improve further."
  - Below 60%: "Keep practicing! Review Java fundamentals and try again."

## OOP Concepts Demonstrated

### Encapsulation
- `QuizQuestion` class encapsulates question data with private members
- Public getter methods for controlled access

### Single Responsibility Principle
- `QuizGame`: Handles GUI and user interaction
- `QuizQuestion`: Represents quiz question data
- `QuizData`: Manages quiz content

### Data Abstraction
- Question complexity hidden from GUI layer
- Clear interfaces between components

### Static Methods
- `QuizData.getQuestions()` provides centralized access to quiz questions
- `SwingUtilities.invokeLater()` for thread-safe GUI updates

## Extending the Application

To add more questions:

1. Open `QuizData.java`
2. Add a new `QuizQuestion` object to the array in `getQuestions()`
3. Recompile and run

Example:
```java
new QuizQuestion(
    "Your question here?",
    new String[]{
        "Option 1",
        "Option 2",
        "Option 3",
        "Option 4"
    },
    0,  // Index of correct answer (0-3)
    "Explanation of the correct answer"
)
```

## Potential Enhancements

- **Question Shuffling**: Randomize question and option order
- **Difficulty Levels**: Easy, Medium, Hard question sets
- **Timed Questions**: Add time constraints for each question
- **Question Categories**: Filter by topic (inheritance, interfaces, etc.)
- **Leaderboard**: Save and display high scores
- **Persistence**: Save quiz progress to file
- **Report Generation**: Export results to PDF
- **Settings Panel**: Customize quiz experience
- **Sound Effects**: Add audio feedback for correct/incorrect answers
- **Animation**: Smooth transitions between questions

## Technical Details

- **GUI Framework**: Java Swing
- **Layout Managers**: BorderLayout, BoxLayout, FlowLayout
- **Event Handling**: ActionListener for button clicks
- **Threading**: SwingUtilities.invokeLater() for GUI thread safety
- **Data Structure**: Array of QuizQuestion objects

## Troubleshooting

### Issue: "javac: command not found" or "java: command not found"
**Solution**: Install JDK and ensure Java is added to your system PATH

### Issue: Application won't run
**Solution**: 
1. Verify all three Java files are in the `src` directory
2. Ensure compilation was successful (no error messages)
3. Check that you're running from the correct directory

### Issue: GUI appears cut off or misaligned
**Solution**: This is unlikely with fixed window size, but if it occurs, check your display scaling settings

## Author Notes

This project was created as an academic OOP project demonstrating:
- Practical application of OOP principles
- GUI development with Java Swing
- Data management and encapsulation
- Event-driven programming

## License

This project is part of an academic assignment and is provided for educational purposes.

## Version History

- **v1.0** (2026-05-03): Initial release with 10 Java fundamentals questions, full GUI implementation, score tracking, and explanations.
