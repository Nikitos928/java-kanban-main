package task;

public enum Status {
    NEW("NEW"),
    DONE("DONE"),
    IN_PROGRESS("IN_PROGRESS");
    private String value;

    private Status(String value) {
        this.value = value;
    }

    public static Status fromString(String value) {
        if (value != null) {
            for (Status pt : Status.values()) {
                if (value.equalsIgnoreCase(pt.value)) {
                    return pt;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }

}
