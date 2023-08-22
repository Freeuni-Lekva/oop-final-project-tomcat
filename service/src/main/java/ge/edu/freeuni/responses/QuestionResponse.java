package ge.edu.freeuni.responses;

import ge.edu.freeuni.models.QuestionModel;

public class QuestionResponse extends ServiceActionResponse{
    private final QuestionModel questionModel;

    public QuestionResponse(boolean success, String errorMessage, QuestionModel questionModel) {
        super(success, errorMessage);
        this.questionModel = questionModel;
    }
    public QuestionModel getQuestionModel(){
        return this.questionModel;
    }
}
