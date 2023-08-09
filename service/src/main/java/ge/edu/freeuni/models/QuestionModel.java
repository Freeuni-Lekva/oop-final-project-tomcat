package ge.edu.freeuni.models;

import ge.edu.freeuni.enums.QuestionType;

import java.util.List;
import java.util.Objects;

public class QuestionModel {
    private final Long id;
    private Long quizId;
    private QuestionType questionType;
    private List<AnswerModel> answers;
    private String imageUrl;
    private int points;

    public QuestionModel(Long id) {
        this.id = id;
    }

    public QuestionModel(Long id, Long quizId, QuestionType questionType, List<AnswerModel> answers, String imageUrl, int points) {
        this.id = id;
        this.quizId = quizId;
        this.questionType = questionType;
        this.answers = answers;
        this.imageUrl = imageUrl;
        this.points = points;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionModel that = (QuestionModel) o;

        if (points != that.points) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(quizId, that.quizId)) return false;
        if (questionType != that.questionType) return false;
        if (!Objects.equals(answers, that.answers)) return false;
        return Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quizId != null ? quizId.hashCode() : 0);
        result = 31 * result + (questionType != null ? questionType.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + points;
        return result;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", questionType=" + questionType +
                ", answers=" + answers +
                ", imageUrl='" + imageUrl + '\'' +
                ", points=" + points +
                '}';
    }
}
