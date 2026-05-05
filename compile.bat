@echo off
REM Compile all Java files
echo Compiling Java files...
cd src
javac QuizQuestion.java QuizData.java QuizGame.java

if %errorlevel% equ 0 (
    echo Compilation successful!
) else (
    echo Compilation failed!
    exit /b 1
)
