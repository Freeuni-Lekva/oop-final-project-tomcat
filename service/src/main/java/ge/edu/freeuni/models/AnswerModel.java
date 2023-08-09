package ge.edu.freeuni.models;

import java.util.Objects;

public class AnswerModel {
    private final Long id;
    private Long questionId;
    private String answer;
    private boolean isCorrect;

    public AnswerModel(Long id) {
        this.id = id;
    }

    public AnswerModel(Long id, Long questionId, String answer, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerModel that = (AnswerModel) o;

        if (isCorrect != that.isCorrect) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(questionId, that.questionId)) return false;
        return Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (isCorrect ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnswerModel{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
