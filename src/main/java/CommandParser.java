import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class CommandParser {
    public static Command parse(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string);
        String type = tokenizer.nextToken().toUpperCase();

        try {
            switch (type) {
                case "SET" -> {
                    String key = tokenizer.nextToken();
                    String value = tokenizer.nextToken();
                    return Command.ofTypeKeyValue(type, key, value);
                }
                case "GET", "DELETE" -> {
                    String key = tokenizer.nextToken();
                    return Command.ofTypeAndKey(type, key);
                }
                case "KEYS", "EXIT" -> {
                    return Command.ofType(type);
                }
                default -> throw new IllegalArgumentException("지원하지 않는 명령어");
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("잘못된 명령어");
        }
    }
}
