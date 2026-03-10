import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DataBase {
    private final Map<String, String> db = new HashMap<>();

    public void set(String key, String value) {
        db.put(key, value);
    }

    public String get(String key) {
        if (!db.containsKey(key)) {
            throw new NoSuchElementException("키가 존재하지 않음");
        }
        return db.get(key);

    }
}
