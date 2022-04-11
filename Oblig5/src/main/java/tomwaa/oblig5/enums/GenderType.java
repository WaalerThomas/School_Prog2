package tomwaa.oblig5.enums;

public enum GenderType {
    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non-Binary");

    public final String label;

    GenderType(String label) {
        this.label = label;
    }
}
