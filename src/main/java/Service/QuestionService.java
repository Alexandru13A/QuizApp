package Service;

import Util.HibernateUtil;
import Model.Question;
import ServiceImpl.QuestionServiceImpl;
import Windows.QuestionUpdate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.util.EventListener;
import java.util.List;

public class QuestionService implements QuestionServiceImpl {

    private static Configuration cfg;
    private static SessionFactory sf;
    private static Session session;


    @Override
    public List<Question> getAllQuestions() {
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Question");
        List<Question> questions = query.list();
        for (Question question : questions) {
            System.out.println("Question" +
                    "id=" + question.getId() +
                    ", question='" + question.getQuestion() + '\'' +
                    ", answer='" + question.getAnswer() + '\'' +
                    ", choice1='" + question.getChoice1() + '\'' +
                    ", choice2='" + question.getChoice2() + '\'' +
                    ", choice3='" + question.getChoice3() + '\'' +
                    ", choice4='" + question.getChoice4());
        }
        return questions;
    }

    @Override
    public Question getQuestionById(long id) {
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Question where id=: id");
        query.setParameter("id", id);

        Question question = (Question) query.uniqueResult();
        System.out.println("Question" +
                "id=" + question.getId() +
                ", question='" + question.getQuestion() + '\'' +
                ", answer='" + question.getAnswer() + '\'' +
                ", choice1='" + question.getChoice1() + '\'' +
                ", choice2='" + question.getChoice2() + '\'' +
                ", choice3='" + question.getChoice3() + '\'' +
                ", choice4='" + question.getChoice4());
        session.getTransaction();
        session.close();
        return question;

    }


    @Override
    public void createQuestion(Question question) {
        cfg = new Configuration().configure();
        sf = cfg.buildSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        session.save(question);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void updateQuestion(long id,String question, int answer,String choice1,String choice2,String choice3,String choice4) {
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Question SET question =: question, answer =: answer,choice1 =: choice1,choice2 =: choice2,choice3 =: choice3,choice4 =: choice4 WHERE id =: id");
        query.setParameter("id", id);
        query.setParameter("question", question);
        query.setParameter("answer", answer);
        query.setParameter("choice1", choice1);
        query.setParameter("choice2", choice2);
        query.setParameter("choice3", choice3);
        query.setParameter("choice4", choice4);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteQuestion(long id) {
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from Question where id =: id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.beginTransaction() != null) {
                session.beginTransaction().rollback();
            }
        }
    }
}
