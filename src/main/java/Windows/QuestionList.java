package Windows;

import Model.Question;
import Service.QuestionService;
import ServiceImpl.QuestionServiceImpl;
import org.hibernate.HibernateException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuestionList extends JFrame {
    private QuestionServiceImpl service = new QuestionService();
    private JPanel contentPane;
    private JTextField id;
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable table = new JTable(dtm);
    private List<Question> questions = service.getAllQuestions();
    private Question question = new Question();


    public QuestionList() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 950, 600);
        setVisible(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().setLayout(null);
        showQuestions();

        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
        backButton.setBounds(10, 532, 101, 21);
        getContentPane().add(backButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 900, 355);
        getContentPane().add(scrollPane);


        scrollPane.setViewportView(table);

        JButton loadQuestions = new JButton("LOAD QUESTIONS");
        loadQuestions.setBounds(435, 375, 140, 21);
        getContentPane().add(loadQuestions);
        loadQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.setRowCount(0);
                questions.clear();
                questions.addAll(service.getAllQuestions());
                showQuestions();

            }
        });


        JButton searchButton = new JButton("SEARCH");
        searchButton.setBounds(52, 431, 101, 21);
        getContentPane().add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.setRowCount(0);
                searchQuestion();
            }
        });

        id = new JTextField();
        id.setBounds(52, 402, 96, 19);
        getContentPane().add(id);
        id.setColumns(10);


        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBounds(170, 431, 101, 21);
        getContentPane().add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteQuestion();
            }
        });

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBounds(290, 431, 101, 21);
        getContentPane().add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQuestion();
            }
        });

        JLabel newLabel = new JLabel("SEARCH BY ID/DELETE BY ID");
        newLabel.setFont(new Font("Arial", Font.BOLD, 9));
        newLabel.setBounds(40, 380, 200, 13);
        getContentPane().add(newLabel);
    }


    private void showQuestions() {
        String header[] = new String[]{"ID", "QUESTION", "CORRECT ANSWER", "CHOICE1", "CHOICE2", "CHOICE3", "CHOICE4"};
        dtm.setColumnIdentifiers(header);
        for (Question question : questions) {
            dtm.addRow(new Object[]{question.getId(), question.getQuestion(), question.getAnswer(), question.getChoice1(),
                    question.getChoice2(), question.getChoice3(), question.getChoice4()});
        }
        dtm.fireTableDataChanged();
    }


    private void deleteQuestion() {
        service.deleteQuestion(Long.valueOf(id.getText()));
        showQuestions();

    }

    private void searchQuestion() {
        String header[] = new String[]{"ID", "QUESTION", "CORRECT ANSWER", "CHOICE1", "CHOICE2", "CHOICE3", "CHOICE4"};
        dtm.setColumnIdentifiers(header);
        question = service.getQuestionById(Long.valueOf(id.getText()));
        dtm.setRowCount(0);
        dtm.addRow(new Object[]{question.getId(), question.getQuestion(), question.getAnswer(), question.getChoice1(),
                question.getChoice2(), question.getChoice3(), question.getChoice4()});


    }

    private void updateQuestion() {
        new QuestionUpdate();
    }

}

