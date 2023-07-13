package entities;


import javax.persistence.*;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name = "from_id")
    private User from;

    @Column(name = "to_id")
    private User to;

    @Column(name = "quiz_url")
    private String quizUrl;

    @Column(name = "Sender_BestScore")
    private int bestScore;


    /**
     * Default constructor.
     */
    public Challenge() {

    }


    public Challenge(Quiz quiz, User from, User to, String quizUrl, int bestScore) {
        this.quiz = quiz;
        this.from = from;
        this.to = to;
        this.quizUrl = quizUrl;
        this.bestScore = bestScore;
    }

    /**
     * Gets the ID of the challenge.
     *
     * @return the ID of the challenge
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the challenge.
     *
     * @param id the ID of the challenge
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Gets the quiz
     *
     * @return the quiz of the challenge
     */
    public Quiz getQuiz(){
        return quiz;
    }


    /**
     * Sets the sender of the challenge.
     *
     * @param quiz the from of the challenge
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }



    /**
     * Gets the sender of the challenge.
     *
     * @return the sender of the challenge
     */
    public User getSender() {
        return from;
    }

    /**
     * Sets the sender of the challenge.
     *
     * @param sender the from of the challenge
     */
    public void setSender(User sender) {
        this.from = sender;
    }


    /**
     * Gets the receiver of the challenge.
     *
     * @return the receiver of the challenge
     */
    public User getReceiver() {
        return to;
    }

    /**
     * Sets the receiver of the challenge.
     *
     * @param receiver the receiver of the challenge
     */
    public void setReceriver(User receiver) {
        this.to = receiver;
    }


    /**
     * Gets the  url
     *
     * @return the quizURL of the challenge
     */
    public String getQuizUrl(){
        return quizUrl;
    }




    /**
     * Sets the quizUrl of the challenge.
     *
     * @param quizUrl the quizUrl of the challenge
     */
    public void setQuizUrl(String quizUrl) {
        this.quizUrl = quizUrl;
    }


    /**
     * Gets the sender's best score
     *
     * @return the bestScore of the challenge
     */
    public int getBestScore(){
        return bestScore;
    }


    /**
     * Sets the sender's best score of the challenge.
     *
     * @param bestScore the bestScore of the challenge
     */
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

}
