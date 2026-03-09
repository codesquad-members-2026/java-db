package com.memdb;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValStorage {

    private Map<String, String> userDB;

    public KeyValStorage(){
        this.userDB = new HashMap<>();
    }

    private boolean contains(String key){
        return this.userDB.containsKey(key);
    }

    public String get(String key) throws Exception {
        if(!contains(key)){
            throw new IllegalArgumentException(" STORAGE::get - KEY DOES NOT EXIST IN THE DB ");
        }

        return this.userDB.get(key);
    }

    public void set(String key, String val){
        this.userDB.put(key,val);
    }

    public void delete(String key) throws Exception{
        if(!contains(key)){
            throw new IllegalArgumentException( " STORAGE::delete - KEY DOES NOT EXIST IN THE DB ");
        }
        this.userDB.remove(key);
    }

    public String keys(){
        if(this.userDB.isEmpty()){
            return "";
        }

        return this.userDB.keySet().stream().collect(Collectors.joining(", "));
    }
}
