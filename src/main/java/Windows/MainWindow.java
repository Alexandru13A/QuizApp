package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    private JFrame frame;
    private JButton insert = new JButton("INSERT QUESTIONS");
    private JButton manageQuestions = new JButton("MANAGE QUESTIONS");
    private JButton startQuiz = new JButton("CREATE A QUIZ");


    public MainWindow() {
        createWindow();
        setLocationAndSize();
        addComponentToFrame();
        actionEvent();

    }

    private void createWindow() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 300);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void setLocationAndSize() {
        insert.setBounds(60, 40, 160, 35);
        manageQuestions.setBounds(60, 90, 160, 35);
        startQuiz.setBounds(60, 140, 160, 35);
    }

    private void addComponentToFrame() {
        frame.add(insert);
        frame.add(manageQuestions);
        frame.add(startQuiz);
    }

    private void actionEvent() {
        insert.addActionListener(this);
        manageQuestions.addActionListener(this);
        startQuiz.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == insert) {
            new QuestionWindow();
        } else if (e.getSource() == manageQuestions) {
            new QuestionList();
        } else if (e.getSource() == startQuiz) {
            new QuizTest();

        }

    }
}
