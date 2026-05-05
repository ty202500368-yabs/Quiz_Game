#!/bin/bash
# Compile all Java files
echo "Compiling Java files..."
cd src
javac QuizQuestion.java QuizData.java QuizGame.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
else
    echo "Compilation failed!"
    exit 1
fi
