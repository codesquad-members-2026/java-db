import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class DataBase {
    private final Map<String, String> store = new HashMap<>();

    public String set(String key, String value) {
        return store.put(key, value);
    }

    public String get(String key) {
        validateKeyExists(key);
        return store.get(key);
    }

    public String delete(String key) {
        validateKeyExists(key);
        return store.remove(key);
    }

    private void validateKeyExists(String key) {
        if (!store.containsKey(key)) {
            throw new NoSuchElementException("키가 존재하지 않음");
        }
    }

    public String getKeys() {
        StringBuilder sb = new StringBuilder();
        for (String key : store.keySet()) {
            sb.append(key).append(" ");
        }
        return sb.toString();
    }
}
