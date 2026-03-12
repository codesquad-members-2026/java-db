import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class DataBase {
    private final Map<String, String> store;

    public DataBase() {
        this(new HashMap<>());
    }

    public DataBase(Map<String, String> store) {
        this.store = store;
    }

    public String set(String key, String value) {
        store.put(key, value);
        return "OK";
    }

    public String get(String key) {
        validateKeyExists(key);
        return store.get(key);
    }

    public String delete(String key) {
        validateKeyExists(key);
        store.remove(key);
        return "OK";
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

    public Map<String, String> getAlls() {
        return store;
    }
}
