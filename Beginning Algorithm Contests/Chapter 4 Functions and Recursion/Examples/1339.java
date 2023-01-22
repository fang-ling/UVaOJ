import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        Integer[] freq1 = new Integer[26];
        Integer[] freq2 = new Integer[26];
        String a = null;
        String b = null;
        while (stdin.hasNext()) {
            Arrays.fill(freq1, 0);
            Arrays.fill(freq2, 0);

            a = stdin.next();
            b = stdin.next();

            a.chars().forEach(i -> {
                    freq1[i - 'A'] += 1;
                });
            b.chars().forEach(i -> {
                    freq2[i - 'A'] += 1;
                });
            Arrays.sort(freq1);
            Arrays.sort(freq2);
            if (Arrays.equals(freq1, freq2)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
