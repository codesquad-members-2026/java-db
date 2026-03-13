package main;

import java.util.*;

public class Storage {

    private final Map<String, String> data = new HashMap<>();

    // SET
    public String save(String key, String value) {
        data.put(key,value);
        return "OK";
    }

    // GET
    public String find(String key) {
        if (!data.containsKey(key)) {
            throw new NoSuchElementException("ERROR. Key Not Found");
        }
        return data.get(key);
    }

    // DELETE
    public String delete(String key) {
        if ( !data.containsKey(key)) {
            throw new NoSuchElementException("ERROR. Key Not Found");
        }
        data.remove(key);
        return "OK";
    }

    // KEYS
    public List<String> findAllKeys() {
        return new ArrayList<>(data.keySet());
    }


}
