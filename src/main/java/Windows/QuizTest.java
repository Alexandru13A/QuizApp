package Windows;

import Model.Question;
import Service.QuizService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizTest extends JFrame implements ActionListener {

    private final QuizService quizService = new QuizService();
    private JFrame frame;
    private final JPanel panel = new JPanel();
    private final JLabel questionNumberLabel = new JLabel("Question:");
    private final JLabel questionLabel = new JLabel("Question 1");
    private final JLabel scoreLabel = new JLabel("Score 0/0");
    private final JButton nextButton = new JButton("Next");
    private final JRadioButton[] answerButtons = new JRadioButton[4];

    private final ButtonGroup answerButtonGroup = new ButtonGroup();


    public QuizTest() {
        createWindow();
        logic();
        setLocationAndSize();
        addComponentToFrame();
        actionEvent();

    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle("QUIZ TEST");
        frame.setBounds(100, 100, 400, 300);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setResizable(true);

    }

    public void logic() {
        answerButtons[0] = new JRadioButton("Answer 1");
        answerButtons[1] = new JRadioButton("Answer 1");
        answerButtons[2] = new JRadioButton("Answer 1");
        answerButtons[3] = new JRadioButton("Answer 1");
        for (JRadioButton answerButton : answerButtons) {
            answerButtonGroup.add(answerButton);
        }
        updateQuestion();


    }

    public void setLocationAndSize() {
        //   panel.setBounds(10, 10, 600, 400);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        questionLabel.setBounds(20, 10, 200, 40);
        scoreLabel.setBounds(20, 210, 200, 35);
        answerButtons[0].setBounds(20, 60, 200, 35);
        answerButtons[1].setBounds(20, 100, 200, 35);
        answerButtons[2].setBounds(20, 140, 200, 35);
        answerButtons[3].setBounds(20, 180, 200, 35);
        nextButton.setBounds(260, 180, 70, 35);
        questionNumberLabel.setBounds(20, 190, 100, 35);

    }


    public void addComponentToFrame() {
        frame.add(questionLabel);
        frame.add(scoreLabel);
        frame.add(answerButtons[0]);
        frame.add(answerButtons[1]);
        frame.add(answerButtons[2]);
        frame.add(answerButtons[3]);
        frame.add(scoreLabel);
        frame.add(panel);
        frame.add(nextButton);
        frame.add(questionNumberLabel);
    }

    public void updateQuestion() {
        Question question = quizService.getCurrentQuestion();
        questionLabel.setText(question.getQuestion());
        answerButtons[0].setText(quizService.getCurrentQuestion().getChoice1());
        answerButtons[1].setText(quizService.getCurrentQuestion().getChoice2());
        answerButtons[2].setText(quizService.getCurrentQuestion().getChoice3());
        answerButtons[3].setText(quizService.getCurrentQuestion().getChoice4());
        answerButtonGroup.clearSelection();
        scoreLabel.setText("Score: " + "Correct: " + quizService.getCorrectAnswers() + " / " + "Wrong: " + quizService.getWrongAnswers());
    }


    private void actionEvent() {
        nextButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (answerButtonGroup.getSelection() != null) {
            int answerIndex = -1;
            for (int i = 0; i < answerButtons.length; i++) {
                if (answerButtons[i].isSelected()) {
                    answerIndex = i;
                    break;
                }
            }
            if (quizService.answerQuestion(answerIndex)) {
                JOptionPane.showMessageDialog(QuizTest.this, "Correct!");
            } else {
                JOptionPane.showMessageDialog(QuizTest.this, "Wrong!");
            }
            if (quizService.hasNextQuestion()) {
                quizService.nextQuestion();
                updateQuestion();
            } else {
                JOptionPane.showMessageDialog(QuizTest.this, "Quiz finished! You answered " + quizService.getCorrectAnswers() + " questions correctly out of " + (quizService.getCorrectAnswers() + quizService.getWrongAnswers()) + " questions. You can close the window.");
                frame.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(QuizTest.this, "Please select an answer!");
        }
    }
}
