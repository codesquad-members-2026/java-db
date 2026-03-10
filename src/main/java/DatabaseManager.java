import java.util.Scanner;
import java.util.StringTokenizer;

public class DatabaseManager {
    private final DataBase db;

    public DatabaseManager(DataBase db) {
        this.db = db;
    }

    public void run() {
        System.out.println("프로그램을 시작합니다");
        Scanner scanner = new Scanner(System.in);

        String readLine = scanner.nextLine();

        while (!readLine.equalsIgnoreCase("EXIT")) {
            try {
                System.out.println(execute(readLine));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            readLine = scanner.nextLine();
        }

        System.out.println("프로그램을 종료합니다");
    }


    public String execute(String getName) {
        StringTokenizer tokenizer = new StringTokenizer(getName);
        String command = tokenizer.nextToken();

        return switch (command.toUpperCase()) {
            case "SET" -> db.set(tokenizer.nextToken(), tokenizer.nextToken());
            case "GET" -> db.get(tokenizer.nextToken());
            case "DELETE" -> db.delete(tokenizer.nextToken());
            case "KEYS" -> db.getKeys();
            default -> throw new IllegalArgumentException("잘못된 명령");
        };
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager(new DataBase());
        databaseManager.run();
    }
}
