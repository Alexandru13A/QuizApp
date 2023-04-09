package ServiceImpl;

import Model.Question;

import java.util.List;

public interface QuestionServiceImpl {

    List<Question> getAllQuestions();

    Question getQuestionById(long id);

    void createQuestion(Question question);

    void updateQuestion(long id,String question, int answer,String choice1,String choice2,String choice3,String choice4);

    void deleteQuestion(long id);


}
