package Windows;

import Model.Question;
import Service.QuestionService;
import ServiceImpl.QuestionServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionWindow extends JFrame implements ActionListener {
    private QuestionServiceImpl questionService = new QuestionService();

    private JFrame frame;
    private JLabel questionLabel = new JLabel("QUESTION");
    private JLabel correctAnswerLabel = new JLabel("CORRECT ANSWER");
    private JLabel wrongAnswerLabel1 = new JLabel("CHOICE1");
    private JLabel wrongAnswerLabel2 = new JLabel("CHOICE2");
    private JLabel wrongAnswerLabel3 = new JLabel("CHOICE3");
    private JLabel wrongAnswerLabel4 = new JLabel("CHOICE4");

    private JTextField questionText = new JTextField();
    private JTextField correctAnswerText = new JTextField();
    private JTextField wrongAnswerText1 = new JTextField();
    private JTextField wrongAnswerText2 = new JTextField();
    private JTextField wrongAnswerText3 = new JTextField();
    private JTextField wrongAnswerText4 = new JTextField();

    private JButton insertButton = new JButton("INSERT");
    private JButton backButton = new JButton("BACK");
    private JButton questionList = new JButton("QUESTIONS");

    public QuestionWindow() {
        createWindow();
        setLocationAndSize();
        addComponentToFrame();
        actionEvent();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle("INSERT QUESTION");
        frame.setBounds(100, 100, 700, 500);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setResizable(true);
    }

    private void setLocationAndSize() {
        questionLabel.setBounds(20, 40, 100, 35);
        correctAnswerLabel.setBounds(20, 80, 150, 35);
        wrongAnswerLabel1.setBounds(20, 120, 150, 35);
        wrongAnswerLabel2.setBounds(20, 160, 150, 35);
        wrongAnswerLabel3.setBounds(20, 200, 150, 35);
        questionText.setBounds(155, 48, 400, 20);
        correctAnswerText.setBounds(155, 88, 200, 20);
        wrongAnswerText1.setBounds(155, 128, 200, 20);
        wrongAnswerText2.setBounds(155, 168, 200, 20);
        wrongAnswerText3.setBounds(155, 208, 200, 20);
        insertButton.setBounds(20, 280, 100, 30);
        backButton.setBounds(130, 280, 100, 30);
        questionList.setBounds(240, 280, 120, 30);
        wrongAnswerLabel4.setBounds(20, 240, 150, 35);
        wrongAnswerText4.setBounds(155, 248, 200, 20);
    }

    private void addComponentToFrame() {
        frame.add(questionLabel);
        frame.add(correctAnswerLabel);
        frame.add(wrongAnswerLabel1);
        frame.add(wrongAnswerLabel2);
        frame.add(wrongAnswerLabel3);
        frame.add(questionText);
        frame.add(correctAnswerText);
        frame.add(wrongAnswerText1);
        frame.add(wrongAnswerText2);
        frame.add(wrongAnswerText3);
        frame.add(insertButton);
        frame.add(backButton);
        frame.add(questionList);
        frame.add(wrongAnswerLabel4);
        frame.add(wrongAnswerText4);

    }

    private void actionEvent() {
        insertButton.addActionListener(this);
        backButton.addActionListener(this);
        questionList.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            Question question = new Question();

            question.setQuestion(questionText.getText());
            question.setAnswer(Integer.valueOf(correctAnswerText.getText()));
            question.setChoice1(wrongAnswerText1.getText());
            question.setChoice2(wrongAnswerText2.getText());
            question.setChoice3(wrongAnswerText3.getText());
            question.setChoice4(wrongAnswerText4.getText());
            questionService.createQuestion(question);
            clearFields();


        } else if (e.getSource() == backButton) {
            frame.dispose();
        } else if (e.getSource() == questionList) {
            new QuestionList();
        }
    }

    private void clearFields() {
        questionText.setText("");
        correctAnswerText.setText("");
        wrongAnswerText1.setText("");
        wrongAnswerText2.setText("");
        wrongAnswerText3.setText("");
        wrongAnswerText4.setText("");
    }
}
