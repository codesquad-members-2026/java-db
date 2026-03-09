package com.db;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InputParser {

    Map<String, InputInterface> commandMap = new HashMap<>();
    KeyValStorage storage;

    public InputParser(KeyValStorage nosql){
        this.storage = nosql;
        this.commandMap.put("SET", (String[] kv) -> {
            this.storage.set(kv[0],kv[1]);
            return "OK";
        });
        this.commandMap.put("GET", (String[] kv) -> {
            try{
                return this.storage.get(kv[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        this.commandMap.put("KEYS", (String[] kv) -> {
            return this.storage.keys();
        });
        this.commandMap.put("DELETE", (String[] kv) -> {
            try{
                this.storage.delete(kv[0]);
                return "OK";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        this.commandMap.put("EXIT", (String[] kv)-> {
            return "EXIT";
        });
    }

    public String runCommand(String userInput){
        String[] splitInput = userInput.split(" ");
        String result = "";
        try{
            result = this.commandMap.get(splitInput[0]).runCommand(Arrays.copyOfRange(splitInput,1,splitInput.length));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
