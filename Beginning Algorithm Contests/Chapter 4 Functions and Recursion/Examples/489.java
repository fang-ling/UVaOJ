import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        Integer round = null;
        Integer current = 0;
        String guess = null;
        String answer = null;
        while (stdin.hasNext()) {
            current = 0;

            round = stdin.nextInt();
            if (round == -1) {
                break;
            }
            answer = stdin.next();
            guess = stdin.next();

            System.out.printf("Round %d\n", round);
            for (int i = 0; i < guess.length(); i += 1) {
                if (answer.indexOf(guess.charAt(i)) >= 0) {
                    answer = answer.replace(guess.charAt(i) + "", "");
                } else {
                    current += 1;
                }
                if (current > 6) {
                    System.out.println("You lose.");
                    break;
                }
                if (answer.length() < 1) {
                    System.out.println("You win.");
                    break;
                }
                if (i == guess.length() - 1) {
                    System.out.println("You chickened out.");
                }
            }
        }
    }
}
