package entities;

import enums.QuestionType;

import javax.persistence.*;

/**
 * Represents a question entity.
 */
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    private QuestionType questionType;

    @Column(name = "answer_1")
    private String answer1;

    @Column(name = "answer_2")
    private String answer2;

    @Column(name = "answer_3")
    private String answer3;

    @Column(name = "answer_4")
    private String answer4;

    @Column(name = "answer_5")
    private String answer5;

    @Column(name = "correct_answer_1")
    private String correctAnswer1;

    @Column(name = "correct_answer_2")
    private String correctAnswer2;

    @Column(name = "correct_answer_3")
    private String correctAnswer3;

    @Column(name = "correct_answer_4")
    private String correctAnswer4;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "points")
    private int points;

    /**
     * Default constructor.
     */
    public Question() {
    }

    /**
     * Constructs a Question object with the specified quiz, question type, answers, correct answers, image URL, and points.
     *
     * @param quiz          the quiz to which the question belongs
     * @param questionType  the type of the question
     * @param answer1       the first possible answer
     * @param answer2       the second possible answer
     * @param answer3       the third possible answer
     * @param answer4       the fourth possible answer
     * @param correctAnswer1 the correct answer for the first possible answer
     * @param correctAnswer2 the correct answer for the second possible answer
     * @param correctAnswer3 the correct answer for the third possible answer
     * @param correctAnswer4 the correct answer for the fourth possible answer
     * @param imageUrl      the image URL associated with the question (for picture-response type)
     * @param points        the score player gets if they guess the question correctly
     */
    public Question(Quiz quiz, QuestionType questionType, String answer1, String answer2, String answer3, String answer4,
                    String answer5, String correctAnswer1, String correctAnswer2, String correctAnswer3, String correctAnswer4,
                    String imageUrl, int points) {
        this.quiz = quiz;
        this.questionType = questionType;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.correctAnswer1 = correctAnswer1;
        this.correctAnswer2 = correctAnswer2;
        this.correctAnswer3 = correctAnswer3;
        this.correctAnswer4 = correctAnswer4;
        this.imageUrl = imageUrl;
        this.points = points;
    }

    /**
     * Gets the ID of the question.
     *
     * @return the ID of the question
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the question.
     *
     * @param id the ID of the question
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the quiz to which the question belongs.
     *
     * @return the quiz to which the question belongs
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Sets the quiz to which the question belongs.
     *
     * @param quiz the quiz to which the question belongs
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Gets the type of the question.
     *
     * @return the type of the question
     */
    public QuestionType getQuestionType() {
        return questionType;
    }

    /**
     * Sets the type of the question.
     *
     * @param questionType the type of the question
     */
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    /**
     * Gets the first possible answer.
     *
     * @return the first possible answer
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * Sets the first possible answer.
     *
     * @param answer1 the first possible answer
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * Gets the second possible answer.
     *
     * @return the second possible answer
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * Sets the second possible answer.
     *
     * @param answer2 the second possible answer
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * Gets the third possible answer.
     *
     * @return the third possible answer
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * Sets the third possible answer.
     *
     * @param answer3 the third possible answer
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    /**
     * Gets the fourth possible answer.
     *
     * @return the fourth possible answer
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * Sets the fourth possible answer.
     *
     * @param answer4 the fourth possible answer
     */
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    /**
     * Gets the fifth possible answer.
     *
     * @return the fifth possible answer
     */
    public String getAnswer5() {
        return answer5;
    }

    /**
     * Sets the fifth possible answer.
     *
     * @param answer5 the fifth possible answer
     */
    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    /**
     * Gets the correct answer for the first possible answer.
     *
     * @return the correct answer for the first possible answer
     */
    public String getCorrectAnswer1() {
        return correctAnswer1;
    }

    /**
     * Sets the correct answer for the first possible answer.
     *
     * @param correctAnswer1 the correct answer for the first possible answer
     */
    public void setCorrectAnswer1(String correctAnswer1) {
        this.correctAnswer1 = correctAnswer1;
    }

    /**
     * Gets the correct answer for the second possible answer.
     *
     * @return the correct answer for the second possible answer
     */
    public String getCorrectAnswer2() {
        return correctAnswer2;
    }

    /**
     * Sets the correct answer for the second possible answer.
     *
     * @param correctAnswer2 the correct answer for the second possible answer
     */
    public void setCorrectAnswer2(String correctAnswer2) {
        this.correctAnswer2 = correctAnswer2;
    }

    /**
     * Gets the correct answer for the third possible answer.
     *
     * @return the correct answer for the third possible answer
     */
    public String getCorrectAnswer3() {
        return correctAnswer3;
    }

    /**
     * Sets the correct answer for the third possible answer.
     *
     * @param correctAnswer3 the correct answer for the third possible answer
     */
    public void setCorrectAnswer3(String correctAnswer3) {
        this.correctAnswer3 = correctAnswer3;
    }

    /**
     * Gets the correct answer for the fourth possible answer.
     *
     * @return the correct answer for the fourth possible answer
     */
    public String getCorrectAnswer4() {
        return correctAnswer4;
    }

    /**
     * Sets the correct answer for the fourth possible answer.
     *
     * @param correctAnswer4 the correct answer for the fourth possible answer
     */
    public void setCorrectAnswer4(String correctAnswer4) {
        this.correctAnswer4 = correctAnswer4;
    }

    /**
     * Gets the image URL associated with the question.
     *
     * @return the image URL associated with the question
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL associated with the question.
     *
     * @param imageUrl the image URL associated with the question
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the score player gets if they guess the question correctly.
     *
     * @return the score player gets if they guess the question correctly
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the score player gets if they guess the question correctly.
     *
     * @param points the score player gets if they guess the question correctly
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
