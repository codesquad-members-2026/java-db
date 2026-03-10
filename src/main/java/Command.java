public class Command {
    private final String type;
    private final String key;
    private final String value;

    private Command(String type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public static Command ofTypeKeyValue(String command, String key, String value) {
        return new Command(command, key, value);
    }

    public static Command ofTypeAndKey(String command, String key) {
        return new Command(command, key, "");
    }

    public static Command ofType(String command) {
        return new Command(command, "", "");
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
