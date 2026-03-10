import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class DataBase {
    private final Map<String, String> db = new HashMap<>();

    public void set(String key, String value) {
        db.put(key, value);
    }

    public String get(String key) {
        validateKeyExists(key);
        return db.get(key);
    }

    public void delete(String key) {
        validateKeyExists(key);
        db.remove(key);
    }

    private void validateKeyExists(String key) {
        if (!db.containsKey(key)) {
            throw new NoSuchElementException("키가 존재하지 않음");
        }
    }

    public Set<String> getKeys() {
        return db.keySet();
    }
}
