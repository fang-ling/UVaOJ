import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static Boolean move(char[][] map, char direction) {
        /* find space */
        int space_i = 0;
        int space_j = 0;
        for (int i = 0; i < 5; i += 1) {
            for (int j = 0; j < 5; j += 1) {
                if (map[i][j] == ' ') {
                    space_i = i;
                    space_j = j;
                }
            }
        }
        if (direction == 'A') {
            if (space_i < 1) {
                return false;
            }
            map[space_i][space_j] = map[space_i - 1][space_j];
            map[space_i - 1][space_j] = ' ';
        } else if (direction == 'B') {
            if (space_i > 3) {
                return false;
            }
            map[space_i][space_j] = map[space_i + 1][space_j];
            map[space_i + 1][space_j] = ' ';
        } else if (direction == 'L') {
            if (space_j < 1) {
                return false;
            }
            map[space_i][space_j] = map[space_i][space_j - 1];
            map[space_i][space_j - 1] = ' ';
        } else if (direction == 'R') {
            if (space_j > 3) {
                return false;
            }
            map[space_i][space_j] = map[space_i][space_j + 1];
            map[space_i][space_j + 1] = ' ';
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        char[][] puzzle = new char[5][5];
        String delta;
        boolean is_ok = false;
        int id = 1;
        StringBuilder sb = null;
        boolean is_first = true;
        while (true) {
            is_ok = true;
            for (int i = 0; i < 5; i += 1) {
                delta = stdin.nextLine();
                if (delta.equals("Z")) {
                    //                    is_end = true;
                    System.exit(0);
                }
                for (int j = 0; j < 5; j += 1) {
                    puzzle[i][j] = delta.charAt(j);
                }
            }
            if (!is_first) System.out.println();
            is_first = false;
            sb = new StringBuilder();
            do {
                delta = stdin.nextLine();
                sb.append(delta);
            } while (delta.charAt(delta.length() - 1) != '0');
            delta = sb.toString();
            delta = delta.substring(0, delta.length() - 1);
            System.out.println("Puzzle #" + id + ":");
            id += 1;
            for (int i = 0; i < delta.length(); i += 1) {
                if (!move(puzzle, delta.charAt(i))) {
                    System.out.
                        println("This puzzle has no final configuration.");
                    is_ok = false;
                    break;
                }
            }
            if (!is_ok) {
                //  System.out.println();
                continue;
            }
            for (int i = 0; i < 5; i += 1) {
                for (int j = 0; j < 5; j += 1) {
                    System.out.print(puzzle[i][j] + (j == 4 ? "" : " "));
                }
                System.out.println();
            }
            //            System.out.println();
        }
    }
}
