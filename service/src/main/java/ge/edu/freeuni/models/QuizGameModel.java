package ge.edu.freeuni.models;

import java.util.List;
import java.util.Objects;

public class QuizGameModel {
    private final Long id;
    private final QuizModel quiz;
    private final UserModel user;
    private Integer score;
    private Integer maxScore;
    private final Long startTimestamp;
    private Long finishTimestamp;

    private String duration;

    public QuizGameModel(Long id, QuizModel quiz, UserModel user, Long startTimestamp) {
        this.id = id;
        this.quiz = quiz;
        this.user = user;
        this.startTimestamp = startTimestamp;
    }

    public QuizGameModel(Long id, QuizModel quiz, UserModel user, Integer score, Integer maxScore, Long startTimestamp, Long finishTimestamp) {
        this.id = id;
        this.quiz = quiz;
        this.user = user;
        this.score = score;
        this.maxScore = maxScore;
        this.startTimestamp = startTimestamp;
        this.finishTimestamp = finishTimestamp;
    }

    public Long getId() {
        return id;
    }

    public QuizModel getQuiz() {
        return quiz;
    }

    public UserModel getUser() {
        return user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public Long getFinishTimestamp() {
        return finishTimestamp;
    }

    public void setFinishTimestamp(Long finishTimestamp) {
        this.finishTimestamp = finishTimestamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizGameModel gameModel = (QuizGameModel) o;

        if (!Objects.equals(id, gameModel.id)) return false;
        if (!Objects.equals(quiz, gameModel.quiz)) return false;
        if (!Objects.equals(user, gameModel.user)) return false;
        if (!Objects.equals(score, gameModel.score)) return false;
        if (!Objects.equals(maxScore, gameModel.maxScore)) return false;
        if (!Objects.equals(startTimestamp, gameModel.startTimestamp))
            return false;
        if (!Objects.equals(finishTimestamp, gameModel.finishTimestamp))
            return false;
        return Objects.equals(duration, gameModel.duration);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quiz != null ? quiz.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (maxScore != null ? maxScore.hashCode() : 0);
        result = 31 * result + (startTimestamp != null ? startTimestamp.hashCode() : 0);
        result = 31 * result + (finishTimestamp != null ? finishTimestamp.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuizGameModel{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", user=" + user +
                ", score=" + score +
                ", maxScore=" + maxScore +
                ", startTimestamp=" + startTimestamp +
                ", finishTimestamp=" + finishTimestamp +
                ", duration='" + duration + '\'' +
                '}';
    }
}
