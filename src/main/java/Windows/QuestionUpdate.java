package Windows;

import Service.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionUpdate extends JFrame implements ActionListener {
    private QuestionService questionService = new QuestionService();
    private JFrame updateFrame;
    private JLabel title = new JLabel("UPDATE QUESTION");
    private JLabel questionLabel = new JLabel("QUESTION");
    private JLabel correctAnswerLabel = new JLabel("CORRECT ANSWER");
    private JLabel wrongAnswerLabel1 = new JLabel("CHOICE1");
    private JLabel wrongAnswerLabel2 = new JLabel("CHOICE2");
    private JLabel wrongAnswerLabel3 = new JLabel("CHOICE3");
    private JLabel wrongAnswerLabel4 = new JLabel("CHOICE4");
    private JLabel idLabel = new JLabel("ID");

    private static JTextField questionText = new JTextField();
    private JTextField idText = new JTextField();
    private static JTextField correctAnswerText = new JTextField();
    private static JTextField wrongAnswerText1 = new JTextField();
    private static JTextField wrongAnswerText2 = new JTextField();
    private static JTextField wrongAnswerText3 = new JTextField();
    private static JTextField wrongAnswerText4 = new JTextField();

    private JButton updateButton = new JButton("UPDATE");
    private JButton backButton = new JButton("BACK");


    public QuestionUpdate() {
        createWindow();
        setLocationAndSize();
        addComponentToFrame();
        actionEvent();
    }

    private void createWindow() {
        updateFrame = new JFrame();
        updateFrame.setTitle("UPDATE QUESTION");
        updateFrame.setBounds(200, 200, 600, 450);
        updateFrame.getContentPane().setBackground(Color.WHITE);
        updateFrame.getContentPane().setLayout(null);
        updateFrame.setVisible(true);
        updateFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        updateFrame.setResizable(true);
    }

    private void setLocationAndSize() {
        title.setBounds(245, 0, 150, 50);
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
        idLabel.setBounds(20, 280, 100, 35);
        idText.setBounds(155, 288, 100, 20);
        updateButton.setBounds(20, 350, 100, 30);
        backButton.setBounds(130, 350, 100, 30);
        wrongAnswerLabel4.setBounds(20, 240, 150, 35);
        wrongAnswerText4.setBounds(155, 248, 200, 20);
    }

    private void addComponentToFrame() {
        updateFrame.add(questionLabel);
        updateFrame.add(correctAnswerLabel);
        updateFrame.add(wrongAnswerLabel1);
        updateFrame.add(wrongAnswerLabel2);
        updateFrame.add(wrongAnswerLabel3);
        updateFrame.add(questionText);
        updateFrame.add(correctAnswerText);
        updateFrame.add(wrongAnswerText1);
        updateFrame.add(wrongAnswerText2);
        updateFrame.add(wrongAnswerText3);
        updateFrame.add(updateButton);
        updateFrame.add(wrongAnswerLabel4);
        updateFrame.add(wrongAnswerText4);
        updateFrame.add(title);
        updateFrame.add(idLabel);
        updateFrame.add(idText);
        updateFrame.add(backButton);
    }

    private void actionEvent() {
        updateButton.addActionListener(this);
        backButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            long id = Long.parseLong(idText.getText());
            String question = questionText.getText();
            int answer = Integer.valueOf(correctAnswerText.getText());
            String choice1 = wrongAnswerText1.getText();
            String choice2 = wrongAnswerText2.getText();
            String choice3 = wrongAnswerText3.getText();
            String choice4 = wrongAnswerText4.getText();
            questionService.updateQuestion(id, question, answer, choice1, choice2, choice3, choice4);
            JOptionPane.showMessageDialog(updateFrame, "Update successfully !");

        } else if (e.getSource() == backButton) {
            updateFrame.dispose();
        }

    }


}
