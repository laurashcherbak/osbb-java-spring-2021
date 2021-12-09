package lpnu.validation.enumeration;

public enum Condition {
    GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS("==");

    private String condition;

    Condition(final String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
