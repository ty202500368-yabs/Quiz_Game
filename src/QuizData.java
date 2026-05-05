/**
 * Contains all quiz questions about Java programming fundamentals.
 */
public class QuizData {
    public static QuizQuestion[] getQuestions() {
        return new QuizQuestion[]{
            new QuizQuestion(
                "What is the correct way to declare a variable in Java?",
                new String[]{
                    "int x = 5;",
                    "x: int = 5;",
                    "int: x = 5;",
                    "var x = 5; (not valid in older Java)"
                },
                0,
                "In Java, you declare a variable by specifying the type first, then the variable name, and optionally assign a value. For example: int x = 5;"
            ),
            new QuizQuestion(
                "Which of the following is NOT a primitive data type in Java?",
                new String[]{
                    "int",
                    "String",
                    "boolean",
                    "double"
                },
                1,
                "String is not a primitive type in Java. It is a reference type (a class). The primitive types are: byte, short, int, long, float, double, boolean, and char."
            ),
            new QuizQuestion(
                "What is the default value of an instance variable of type int?",
                new String[]{
                    "0",
                    "null",
                    "1",
                    "undefined"
                },
                0,
                "The default value of an int instance variable is 0. All numeric primitive types default to 0, boolean defaults to false, and reference types default to null."
            ),
            new QuizQuestion(
                "Which keyword is used to create a new object in Java?",
                new String[]{
                    "new",
                    "create",
                    "make",
                    "instantiate"
                },
                0,
                "The 'new' keyword is used to create a new object (instantiate a class) in Java. For example: MyClass obj = new MyClass();"
            ),
            new QuizQuestion(
                "What does OOP stand for?",
                new String[]{
                    "Object-Oriented Programming",
                    "Object-Oriented Protocol",
                    "Object-Order Programming",
                    "Organized Object Program"
                },
                0,
                "OOP stands for Object-Oriented Programming. It is a programming paradigm based on the concept of objects and classes."
            ),
            new QuizQuestion(
                "Which access modifier is most restrictive?",
                new String[]{
                    "private",
                    "protected",
                    "public",
                    "default (no modifier)"
                },
                0,
                "The 'private' modifier is the most restrictive. It limits access to only within the same class. Other modifiers are: default (package), protected (package and subclasses), and public (everywhere)."
            ),
            new QuizQuestion(
                "What is inheritance in Java?",
                new String[]{
                    "A mechanism for a class to inherit properties and methods from another class",
                    "A way to create multiple objects from one class",
                    "A method to copy code between classes",
                    "A way to call methods from other classes"
                },
                0,
                "Inheritance is a mechanism where a derived class (subclass) inherits properties and methods from a base class (superclass) using the 'extends' keyword."
            ),
            new QuizQuestion(
                "Which method must every class that implements Runnable provide?",
                new String[]{
                    "run()",
                    "start()",
                    "main()",
                    "execute()"
                },
                0,
                "Any class that implements the Runnable interface must provide the run() method. This method contains the code that will be executed in the thread."
            ),
            new QuizQuestion(
                "What is polymorphism?",
                new String[]{
                    "The ability of an object to take many forms",
                    "The ability to inherit from multiple classes",
                    "The ability to create multiple objects",
                    "The ability to use multiple threads"
                },
                0,
                "Polymorphism is the ability of an object to take multiple forms. It is typically achieved through method overriding and interfaces."
            ),
            new QuizQuestion(
                "What is an abstract class?",
                new String[]{
                    "A class that cannot be instantiated and may contain abstract methods",
                    "A class that is not used in the program",
                    "A class with only private members",
                    "A class with no methods"
                },
                0,
                "An abstract class cannot be instantiated directly. It is declared with the 'abstract' keyword and can contain abstract methods (methods without implementation) that subclasses must implement."
            )
        };
    }
}
