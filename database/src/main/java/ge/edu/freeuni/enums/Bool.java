package ge.edu.freeuni.enums;

public enum Bool {
    FALSE(false),
    TRUE(true);

    private final boolean value;

    Bool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public static Bool getByValue(boolean value) {
        for (Bool val : values()) {
            if (val.value == value) {
                return val;
            }
        }
        throw new IllegalArgumentException("No Bool with value: " + value);
    }
}
