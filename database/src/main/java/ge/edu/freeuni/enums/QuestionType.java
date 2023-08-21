package ge.edu.freeuni.enums;

public enum QuestionType {
    QUESTION_RESPONSE(1),
    FILL_IN(2),
    MULTIPLE_CHOICE(3),
    PICTURE_RESPONSE(4);

    private final int value;

    QuestionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuestionType getByValue(int value) {
        for (QuestionType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("No QuestionType with value: " + value);
    }
}
