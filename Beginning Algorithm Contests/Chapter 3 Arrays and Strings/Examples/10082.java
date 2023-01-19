import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main10082 {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        String keyboard_str = "`1234567890-=" +
                              "QWERTYUIOP[]\\" +
                              "ASDFGHJKL;'" +
                              "ZXCVBNM,./";
        char[] keyboard = keyboard_str.toCharArray();

        String line = null;
        char[] delta = null;
        while (stdin.hasNext()) {
            line = stdin.nextLine();
            delta = line.toCharArray();
            for (char c : delta) {
                if (c == ' ') {
                    System.out.print(c);
                    continue;
                }
                for (int i = 0; i < keyboard.length; i += 1) {
                    if (keyboard[i] == c) {
                        System.out.print(keyboard[i - 1]);
                        break;
                    }
                }
            }
            System.out.println();
        }
    }
}
