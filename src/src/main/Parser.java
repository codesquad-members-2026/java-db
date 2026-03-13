package main;

public class Parser {

    private final Storage storage;

    public Parser (Storage storage) {
        this.storage = storage;
    }

    public void parse(String input) {
        String[] words = input.split(" ");
        String command = words[0];

        if (command.equals("SET")) {
            if (words.length != 3) {
                throw new IllegalArgumentException("ERROR : ex) SET name value");
            }
            String result =  storage.save(words[1], words[2]);
            System.out.println(result);
        }

        if (command.equals("GET")) {
            if (words.length != 2) {
                throw new IllegalArgumentException("ERROR : ex) GET name");
            }
            System.out.println(storage.find(words[1]));
        }

        if (command.equals("DELETE")) {
            if (words.length != 2) {
                throw new IllegalArgumentException("ERROR : ex) DELETE name");
            }
            String result = storage.delete(words[1]);
            System.out.println(result);
        }

        if (command.equals("KEYS")) {
            System.out.println(storage.findAllKeys());
        }

    }

}
