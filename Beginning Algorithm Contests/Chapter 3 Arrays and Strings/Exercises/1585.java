import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = stdin.nextInt();

        String delta = null;
        String[] array = null;
        int score = 0;
        while (t > 0) {
            score = 0;
            delta = stdin.next();
            array = delta.split("X+");
            for (String i : array) {
                score += (i.length() + 1) * i.length() / 2;
            }
            System.out.println(score);
            
            t -= 1;
        }
    }
}
