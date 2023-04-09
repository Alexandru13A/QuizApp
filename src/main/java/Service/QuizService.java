package Service;

import Model.Question;

import java.util.List;

public class QuizService {
    private QuestionService service = new QuestionService();
    private List<Question> questions;
    private int currentQuestion = 0;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;


   public QuizService() {
        questions = service.getAllQuestions();
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestion);
    }

    public boolean answerQuestion(int answerIndex) {
        Question question = getCurrentQuestion();
        if (answerIndex == question.getAnswer()) {
            correctAnswers++;
            return true;
        } else {
            wrongAnswers++;
            return false;
        }
    }

    public boolean hasNextQuestion() {
        return currentQuestion < questions.size() - 1;
    }

    public void nextQuestion() {
        currentQuestion++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }


}
