import java.awt.*;
import javax.swing.*;

/**
 * Custom panel to draw the snail character and progress track.
 */
class SnailProgressPanel extends JPanel {
    private int snailPosition; // 0 to 10 (number of questions)
    private int totalQuestions;
    private int mistakes;

    public SnailProgressPanel(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        this.snailPosition = 0;
        this.mistakes = 0;
        setPreferredSize(new Dimension(800, 100));
        setBackground(new Color(240, 248, 255));
    }

    public void setSnailPosition(int position) {
        this.snailPosition = position;
        repaint();
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int trackY = panelHeight / 2;
        int trackStartX = 50;
        int trackEndX = panelWidth - 50;
        int trackWidth = trackEndX - trackStartX;

        // Draw track (finish line)
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(trackStartX, trackY, trackEndX, trackY);

        // Draw question markers on track
        g2d.setColor(new Color(150, 150, 150));
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        for (int i = 0; i <= totalQuestions; i++) {
            int x = trackStartX + (trackWidth * i) / totalQuestions;
            g2d.fillOval(x - 3, trackY - 3, 6, 6);
            if (i == 0 || i == totalQuestions) {
                g2d.drawString(String.valueOf(i), x - 5, trackY - 15);
            }
        }

        // Calculate snail position
        int snailX = trackStartX + (trackWidth * snailPosition) / totalQuestions;

        // Draw snail shell (circle)
        g2d.setColor(new Color(139, 69, 19)); // Brown
        g2d.fillOval(snailX - 15, trackY - 25, 30, 30);

        // Draw snail shell pattern (spiral)
        g2d.setColor(new Color(160, 82, 45)); // Lighter brown
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(snailX - 10, trackY - 20, 20, 20);
        g2d.drawOval(snailX - 6, trackY - 16, 12, 12);

        // Draw snail body (rectangle)
        g2d.setColor(new Color(200, 150, 100)); // Beige
        g2d.fillRect(snailX - 8, trackY, 16, 15);

        // Draw snail head (small circle)
        g2d.setColor(new Color(200, 150, 100));
        g2d.fillOval(snailX - 6, trackY + 12, 12, 12);

        // Draw snail eyes
        g2d.setColor(Color.BLACK);
        g2d.fillOval(snailX - 3, trackY + 14, 2, 2);
        g2d.fillOval(snailX + 2, trackY + 14, 2, 2);

        // Draw snail tentacles
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(snailX - 3, trackY + 12, snailX - 5, trackY + 5);
        g2d.drawLine(snailX + 3, trackY + 12, snailX + 5, trackY + 5);

        // Draw mistake counter
        g2d.setColor(new Color(220, 20, 60)); // Crimson
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        String mistakeText = "Mistakes: " + mistakes + "/3";
        g2d.drawString(mistakeText, 20, 25);

        if (mistakes == 3) {
            g2d.setColor(new Color(255, 0, 0));
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.drawString("⚠ GAME OVER - RESTARTING", panelWidth / 2 - 100, 25);
        }
    }
}

/**
 * Main GUI frame for the Java Quiz Game.
 * Displays questions and manages the quiz flow with an interactive snail character.
 */
public class QuizGame extends JFrame {
    private QuizQuestion[] questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int mistakes = 0;
    private int selectedAnswer = -1;

    // GUI Components
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JLabel scoreLabel;
    private JLabel questionCounterLabel;
    private JButton nextButton;
    private JButton previousButton;
    private JTextArea explanationArea;
    private JPanel questionPanel;
    private SnailProgressPanel snailPanel;
    private boolean gameComplete = false;
    private boolean answerProcessing = false;

    public QuizGame() {
        setTitle("Java Programming Quiz Game - Interactive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Load questions
        questions = QuizData.getQuestions();

        // Initialize GUI
        initializeGUI();
        displayQuestion(0);

        setVisible(true);
    }

    private void initializeGUI() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBackground(new Color(240, 248, 255));

        // Snail progress panel
        snailPanel = new SnailProgressPanel(questions.length);
        snailPanel.setBorder(BorderFactory.createTitledBorder("Progress - Help the Snail Reach the Finish!"));
        snailPanel.setBackground(new Color(240, 248, 255));

        // Top panel with score and question counter
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(70, 130, 180));
        
        scoreLabel = new JLabel("Score: 0/" + questions.length);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        questionCounterLabel = new JLabel("Question 1 of " + questions.length);
        questionCounterLabel.setForeground(Color.WHITE);
        questionCounterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        topPanel.add(scoreLabel, BorderLayout.WEST);
        topPanel.add(questionCounterLabel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Center panel with questions
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(new Color(240, 248, 255));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        questionPanel.add(questionLabel);
        questionPanel.add(Box.createVerticalStrut(15));

        // Options
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            optionButtons[i].setBackground(new Color(240, 248, 255));
            optionButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            optionButtons[i].setMaximumSize(new Dimension(Short.MAX_VALUE, 35));
            
            // Add listener to auto-submit on last question
            final int index = i;
            optionButtons[i].addActionListener(e -> {
                if (currentQuestionIndex == questions.length - 1) {
                    // Auto-proceed on last question after a short delay
                    Timer autoSubmitTimer = new Timer(500, ae -> {
                        if (optionButtons[index].isSelected()) {
                            goToNextQuestion();
                        }
                    });
                    autoSubmitTimer.setRepeats(false);
                    autoSubmitTimer.start();
                }
            });
            
            optionGroup.add(optionButtons[i]);
            questionPanel.add(optionButtons[i]);
            questionPanel.add(Box.createVerticalStrut(8));
        }

        // Explanation area
        explanationArea = new JTextArea();
        explanationArea.setFont(new Font("Arial", Font.PLAIN, 12));
        explanationArea.setLineWrap(true);
        explanationArea.setWrapStyleWord(true);
        explanationArea.setEditable(false);
        explanationArea.setBackground(new Color(255, 250, 205));
        explanationArea.setBorder(BorderFactory.createTitledBorder("Explanation"));
        explanationArea.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        questionPanel.add(Box.createVerticalStrut(10));
        questionPanel.add(explanationArea);
        questionPanel.add(Box.createVerticalGlue());

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(240, 248, 255));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        previousButton = new JButton("Previous");
        previousButton.setFont(new Font("Arial", Font.PLAIN, 12));
        previousButton.setPreferredSize(new Dimension(100, 35));
        previousButton.addActionListener(e -> goToPreviousQuestion());

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 12));
        nextButton.setPreferredSize(new Dimension(100, 35));
        nextButton.addActionListener(e -> goToNextQuestion());

        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);

        // Create a north panel to hold both snail and top panels
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBackground(new Color(240, 248, 255));
        northPanel.add(snailPanel);
        northPanel.add(topPanel);

        // Add panels to content pane
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(questionPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void displayQuestion(int index) {
        currentQuestionIndex = index;
        QuizQuestion question = questions[index];

        // Update question label
        questionLabel.setText("<html>" + question.getQuestion() + "</html>");

        // Update options
        String[] options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setSelected(false);
        }

        // Clear explanation
        explanationArea.setText("");
        selectedAnswer = -1;

        // Update labels - cap score at 10
        int displayScore = Math.min(score, questions.length);
        scoreLabel.setText("Score: " + displayScore + "/" + questions.length);
        questionCounterLabel.setText("Question " + (index + 1) + " of " + questions.length);

        // Update snail position
        snailPanel.setSnailPosition(index);
        snailPanel.setMistakes(mistakes);

        // Update button states
        previousButton.setEnabled(index > 0);
        nextButton.setEnabled(index < questions.length - 1 && !gameComplete);
    }

    private void goToNextQuestion() {
        // Prevent double-clicking or processing while another answer is being processed
        if (answerProcessing || gameComplete) {
            return;
        }
        
        answerProcessing = true;
        
        if (currentQuestionIndex < questions.length - 1) {
            // Save the current answer
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedAnswer = i;
                    QuizQuestion currentQuestion = questions[currentQuestionIndex];
                    
                    // Check if answer is correct
                    if (currentQuestion.isCorrect(i)) {
                        if (score < questions.length) {
                            score++;
                        }
                        explanationArea.setText("✓ Correct!\n\n" + currentQuestion.getExplanation());
                        explanationArea.setBackground(new Color(144, 238, 144)); // Light green
                    } else {
                        mistakes++;
                        explanationArea.setText("✗ Incorrect!\n\nCorrect answer: " + 
                            currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()] + 
                            "\n\n" + currentQuestion.getExplanation());
                        explanationArea.setBackground(new Color(255, 182, 193)); // Light red
                        
                        // Check if player reached 3 mistakes
                        if (mistakes >= 3) {
                            snailPanel.setMistakes(mistakes);
                            Timer delayTimer = new Timer(1500, e -> {
                                JOptionPane.showMessageDialog(this, 
                                    "You've made 3 mistakes!\n\nThe snail has to go back to the start.\nLet's try again!",
                                    "Game Reset",
                                    JOptionPane.WARNING_MESSAGE);
                                resetGame();
                            });
                            delayTimer.setRepeats(false);
                            delayTimer.start();
                            return;
                        }
                    }
                    break;
                }
            }
            
            // Display next question after a short delay
            Timer timer = new Timer(1500, e -> {
                explanationArea.setBackground(new Color(255, 250, 205)); // Reset color
                answerProcessing = false;
                displayQuestion(currentQuestionIndex + 1);
            });
            timer.setRepeats(false);
            timer.start();
        } else if (currentQuestionIndex == questions.length - 1) {
            // Handle last question
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedAnswer = i;
                    QuizQuestion currentQuestion = questions[currentQuestionIndex];
                    
                    if (currentQuestion.isCorrect(i)) {
                        if (score < questions.length) {
                            score++;
                        }
                        explanationArea.setText("✓ Correct!\n\n" + currentQuestion.getExplanation());
                        explanationArea.setBackground(new Color(144, 238, 144));
                    } else {
                        mistakes++;
                        explanationArea.setText("✗ Incorrect!\n\nCorrect answer: " + 
                            currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()] + 
                            "\n\n" + currentQuestion.getExplanation());
                        explanationArea.setBackground(new Color(255, 182, 193));
                        
                        // Check if player reached 3 mistakes
                        if (mistakes >= 3) {
                            snailPanel.setMistakes(mistakes);
                            Timer delayTimer = new Timer(1500, e -> {
                                JOptionPane.showMessageDialog(this, 
                                    "You've made 3 mistakes!\n\nThe snail has to go back to the start.\nLet's try again!",
                                    "Game Reset",
                                    JOptionPane.WARNING_MESSAGE);
                                resetGame();
                            });
                            delayTimer.setRepeats(false);
                            delayTimer.start();
                            return;
                        }
                    }
                    break;
                }
            }
            
            // Show quiz complete after a delay
            Timer timer = new Timer(1500, e -> {
                gameComplete = true;
                showQuizComplete();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void goToPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            displayQuestion(currentQuestionIndex - 1);
        }
    }

    private void resetGame() {
        score = 0;
        mistakes = 0;
        currentQuestionIndex = 0;
        gameComplete = false;
        answerProcessing = false;
        displayQuestion(0);
    }

    private void showQuizComplete() {
        snailPanel.setSnailPosition(questions.length);
        // Cap score at 10
        int finalScore = Math.min(score, questions.length);
        int percentage = (finalScore * 100) / questions.length;
        String message = "🐌 The Snail Made It! Quiz Complete!\n\n" +
                        "Your Score: " + finalScore + " out of " + questions.length + "\n" +
                        "Percentage: " + percentage + "%\n" +
                        "Mistakes: " + mistakes + "/3\n\n";

        // Score-based feedback
        if (finalScore == 10) {
            message += "🎉 CONGRATULATIONS! 🎉\n" +
                      "Perfect Score! You are a Java Master!";
        } else if (finalScore >= 6) {
            message += "✅ Congrats! You can still do better!\n" +
                      "Keep practicing to reach a perfect score!";
        } else if (finalScore < 6) {
            message += "⚠️ You need to study better!\n" +
                      "Review Java fundamentals and try again!";
        }

        JOptionPane.showMessageDialog(this, message, "Quiz Complete - Snail Victory!", JOptionPane.INFORMATION_MESSAGE);

        // Ask to play again
        int result = JOptionPane.showConfirmDialog(this, 
            "Would you like to play again?", 
            "Play Again?", 
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizGame());
    }
}
