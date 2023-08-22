package ge.edu.freeuni.models;

import java.util.List;
import java.util.Objects;

public class QuizModel {
    private final Long id;
    private String name;
    private String description;
    private UserModel owner;
    private List<QuestionModel> questions;

    private Integer randomizeQuestions;
    private Integer onePage;
    private Integer immediateCorrection;
    private Integer practiceMode;
    private final Long creationTimestamp;

    public QuizModel(Long id, Long creationTimestamp) {
        this.id = id;
        this.creationTimestamp = creationTimestamp;
    }

    public QuizModel(Long id, String name, String description, UserModel owner, List<QuestionModel> questions, Integer randomizeQuestions, Integer onePage, Integer immediateCorrection, Integer practiceMode, Long creationTimestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.questions = questions;
        this.randomizeQuestions = randomizeQuestions;
        this.onePage = onePage;
        this.immediateCorrection = immediateCorrection;
        this.practiceMode = practiceMode;
        this.creationTimestamp = creationTimestamp;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }

    public Long getCreationTimestamp() {
        return creationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizModel quizModel = (QuizModel) o;

        if (!Objects.equals(id, quizModel.id)) return false;
        if (!Objects.equals(name, quizModel.name)) return false;
        if (!Objects.equals(description, quizModel.description))
            return false;
        if (!Objects.equals(owner, quizModel.owner)) return false;
        if (!Objects.equals(questions, quizModel.questions)) return false;
        return Objects.equals(creationTimestamp, quizModel.creationTimestamp);
    }

    public Integer getRandomizeQuestions() {
        return randomizeQuestions;
    }

    public void setRandomizeQuestions(Integer randomizeQuestions) {
        this.randomizeQuestions = randomizeQuestions;
    }

    public Integer getOnePage() {
        return onePage;
    }

    public void setOnePage(Integer onePage) {
        this.onePage = onePage;
    }

    public Integer getImmediateCorrection() {
        return immediateCorrection;
    }

    public void setImmediateCorrection(Integer immediateCorrection) {
        this.immediateCorrection = immediateCorrection;
    }

    public Integer getPracticeMode() {
        return practiceMode;
    }

    public void setPracticeMode(Integer practiceMode) {
        this.practiceMode = practiceMode;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (creationTimestamp != null ? creationTimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuizModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", questions=" + questions +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
