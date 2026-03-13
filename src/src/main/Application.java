package main;

import java.util.Scanner;

public class Application {


    private final Parser parser;

    public Application(Parser parser) {
        this.parser = parser;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("EXIT")) break;

            try {
                parser.parse(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
