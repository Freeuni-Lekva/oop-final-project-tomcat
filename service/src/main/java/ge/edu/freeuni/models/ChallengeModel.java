package ge.edu.freeuni.models;

import ge.edu.freeuni.entities.Challenge;
import ge.edu.freeuni.entities.Quiz;
import ge.edu.freeuni.entities.User;

import java.util.Objects;

public class ChallengeModel {
    private final Long id;
    private QuizModel quizModel;
    private UserModel from;
    private UserModel to;
    private String quizUrl;
    private int bestScore;

    public ChallengeModel(Long id) {
        this.id = id;
    }

    public ChallengeModel(Long id, QuizModel quizModel, UserModel from, UserModel to, String quizUrl, int bestScore) {
        this.id = id;
        this.quizModel = quizModel;
        this.from = from;
        this.to = to;
        this.quizUrl = quizUrl;
        this.bestScore = bestScore;
    }

    public Long getId() {
        return id;
    }

    public QuizModel getQuiz() {
        return quizModel;
    }



    public void setQuiz(QuizModel quiz) {
        this.quizModel = quizModel;
    }



    public UserModel getSender() {
        return from;
    }


    public void setSender(UserModel sender) {
        this.from = sender;
    }



    public UserModel getReceiver() {
        return to;
    }


    public void setReceriver(UserModel receiver) {
        this.to = receiver;
    }

    public String getQuizUrl() {
        return quizUrl;
    }



    public void setQuizUrl(String quizUrl) {
        this.quizUrl = quizUrl;
    }



    public int getBestScore() {
        return bestScore;
    }


    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeModel challenge = (ChallengeModel) o;
        return bestScore == challenge.bestScore && Objects.equals(id, challenge.id) && Objects.equals(quizModel, challenge.quizModel) && Objects.equals(from, challenge.from) && Objects.equals(to, challenge.to) && Objects.equals(quizUrl, challenge.quizUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizModel, from, to, quizUrl, bestScore);
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", quiz=" + quizModel +
                ", from=" + from +
                ", to=" + to +
                ", quizUrl='" + quizUrl + '\'' +
                ", bestScore=" + bestScore +
                '}';
    }


}
