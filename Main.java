import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Board board = new Board(2, scan);
        scan.close();
    }
}
