package main;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Parser parser = new Parser(storage);

        Application app = new Application(parser);
        app.run();
    }
}