package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.Answer;
import ge.edu.freeuni.entities.Question;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.enums.Bool;
import ge.edu.freeuni.enums.QuestionType;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.QuestionResponse;
import ge.edu.freeuni.util.EntityToModelBridge;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    private DAO<Question> questionDao = DAOFactory.getInstance().getDAO(Question.class);

    private DAO<Answer> answerDAO = DAOFactory.getInstance().getDAO(Answer.class);


    public QuestionResponse addResponseQuestion(Quiz quiz, String questionText, String answer) {
        if(quiz == null){
            return new QuestionResponse(false, "there was a problem with quiz creation,\n" +
                    " try again later ", null);
        }
        if(questionText.isEmpty() || answer == null || answer.isEmpty()){
            return new QuestionResponse(false, "both, question and answer should be provided by the user", null);
        }
        Question newQuestion = new Question();
        newQuestion.setQuestion(questionText);
        newQuestion.setQuestionType(QuestionType.QUESTION_RESPONSE);
        newQuestion.setQuiz(quiz);

        Answer answer1 = new Answer(newQuestion,answer, Bool.TRUE,null);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);

        newQuestion.setAnswers(answers);

            return new QuestionResponse(true,null, EntityToModelBridge.toQuestionModel(newQuestion));

    }

    public QuestionResponse addFillInQuestion(Quiz quiz, String beforeGap, String answer, String afterGap) {
        if(quiz == null){
            return new QuestionResponse(false, "there was a problem with quiz creation,\n" +
                    " try again later ", null);
        }
        if(answer == null || answer.isEmpty()){
            return new QuestionResponse(false, "the answer should be provided by the user", null);
        }
        if((beforeGap == null || beforeGap.isEmpty()) && (afterGap == null || afterGap.isEmpty())){
            return new QuestionResponse(false, "the fill in gap sentence can not be empty", null);
        }

        Question newQuestion = new Question();
        newQuestion.setBeforeBlank(beforeGap);
        newQuestion.setAfterBlank(afterGap);
        newQuestion.setQuestionType(QuestionType.FILL_IN);
        newQuestion.setQuiz(quiz);

        Answer answer1 = new Answer(newQuestion,answer, Bool.TRUE,null);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);

        newQuestion.setAnswers(answers);

            return new QuestionResponse(true,null, EntityToModelBridge.toQuestionModel(newQuestion));


    }

    public QuestionResponse addMultipleChoiceQuestion(Quiz quiz, String questionText, List<String> allAnswers, Integer indexOfCorrectAnswer) {
        if(quiz == null){
            return new QuestionResponse(false, "there was a problem with quiz creation,\n" +
                    " try again later ", null);
        }
        if(allAnswers == null || allAnswers.isEmpty()){
            return new QuestionResponse(false, "the answers should be provided by the user", null);
        }
        if(indexOfCorrectAnswer == null){
            return new QuestionResponse(false, "the correct answer should be provided by the user", null);
        }
        if(questionText == null || questionText.isEmpty()){
            return new QuestionResponse(false, "the question should be provided by the user", null);
        }

        Question question = new Question();
        question.setQuestion(questionText);
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        question.setQuiz(quiz);

        List<Answer> answerList = new ArrayList<>();
        for(int i = 0; i< allAnswers.size();i++){
            if(i == indexOfCorrectAnswer){
                answerList.add(new Answer(question,allAnswers.get(i),Bool.TRUE,null));
            }
            answerList.add(new Answer(question,allAnswers.get(i),Bool.FALSE,null));
        }
        question.setAnswers(answerList);

            return new QuestionResponse(true,null,EntityToModelBridge.toQuestionModel(question));

    }

    public QuestionResponse addImageResonseQuestion(Quiz quiz, String imageURL, String answer) {
        if(quiz == null){
            return new QuestionResponse(false, "there was a problem with quiz creation,\n" +
                    " try again later ", null);
        }
        if(imageURL.isEmpty() || answer == null || answer.isEmpty()){
            return new QuestionResponse(false, "both, image and answer should be provided by the user", null);
        }
        Question newQuestion = new Question();
        newQuestion.setImageUrl(imageURL);
        newQuestion.setQuestionType(QuestionType.PICTURE_RESPONSE);
        newQuestion.setQuiz(quiz);

        Answer answer1 = new Answer(newQuestion,answer, Bool.TRUE,null);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);

        newQuestion.setAnswers(answers);

            return new QuestionResponse(true,null, EntityToModelBridge.toQuestionModel(newQuestion));

    }
}
