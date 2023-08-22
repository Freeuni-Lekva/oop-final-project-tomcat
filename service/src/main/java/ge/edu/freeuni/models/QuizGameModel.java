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
    private boolean practice;
    public QuizGameModel(Long id, QuizModel quiz, UserModel user, Long startTimestamp, boolean practice) {
        this.id = id;
        this.quiz = quiz;
        this.user = user;
        this.startTimestamp = startTimestamp;
        this.practice = practice;
    }

    public QuizGameModel(Long id, QuizModel quiz, UserModel user, Integer score, Integer maxScore, Long startTimestamp, Long finishTimestamp, boolean practice) {
        this.id = id;
        this.quiz = quiz;
        this.user = user;
        this.score = score;
        this.maxScore = maxScore;
        this.startTimestamp = startTimestamp;
        this.finishTimestamp = finishTimestamp;
        this.practice = practice;
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

    public boolean isPractice() {
        return practice;
    }

    public void setPractice(boolean practice) {
        this.practice = practice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizGameModel that = (QuizGameModel) o;

        if (practice != that.practice) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(quiz, that.quiz)) return false;
        if (!Objects.equals(user, that.user)) return false;
        if (!Objects.equals(score, that.score)) return false;
        if (!Objects.equals(maxScore, that.maxScore)) return false;
        if (!Objects.equals(startTimestamp, that.startTimestamp))
            return false;
        if (!Objects.equals(finishTimestamp, that.finishTimestamp))
            return false;
        return Objects.equals(duration, that.duration);
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
        result = 31 * result + (practice ? 1 : 0);
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
                ", practice=" + practice +
                '}';
    }
}
