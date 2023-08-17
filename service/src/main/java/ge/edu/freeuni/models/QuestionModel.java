package ge.edu.freeuni.models;

import ge.edu.freeuni.enums.QuestionType;

import java.util.List;
import java.util.Objects;

public class QuestionModel {
    private final Long id;
    private Long quizId;
    private String question;
    private QuestionType questionType;
    private List<AnswerModel> answers;
    private String imageUrl;

    public QuestionModel(Long id) {
        this.id = id;
    }

    public QuestionModel(Long id, Long quizId, String question, QuestionType questionType, List<AnswerModel> answers, String imageUrl) {
        this.id = id;
        this.quizId = quizId;
        this.question = question;
        this.questionType = questionType;
        this.answers = answers;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionModel that = (QuestionModel) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(quizId, that.quizId)) return false;
        if (!Objects.equals(question, that.question)) return false;
        if (questionType != that.questionType) return false;
        if (!Objects.equals(answers, that.answers)) return false;
        return Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quizId != null ? quizId.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (questionType != null ? questionType.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", question='" + question + '\'' +
                ", questionType=" + questionType +
                ", answers=" + answers +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
