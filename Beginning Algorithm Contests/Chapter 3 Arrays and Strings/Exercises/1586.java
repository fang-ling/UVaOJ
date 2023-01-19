import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = stdin.nextInt();
        char[] delta = null;
        StringBuilder alpha = null;
        String[] let = null;
        String[] num = null;
        double ans = 0;
        int count = 0;
        while (t > 0) {
            delta = stdin.next().toCharArray();
            alpha = new StringBuilder();
            for (int i = 0, len = delta.length - 1; i < len; i += 1) {
                alpha.append(delta[i]);
                if (Character.isLetter(delta[i]) &&
                    Character.isLetter(delta[i + 1])) { /* Ex: CH... */
                    alpha.append('1');
                }
            }
            alpha.append(delta[delta.length - 1]); /* Add last char */
            /* Ex: C or ...C1O1O1H */
            if (Character.isLetter(alpha.charAt(alpha.length() - 1))) {
                alpha.append('1');
            }
            let = alpha.toString().split("\\d+");
            num = alpha.toString().split("[CHON]");
            ans = 0;
            for (int i = 0; i < let.length; i += 1) {
                count = Integer.parseInt(num[i + 1]);
                if (let[i].equals("C")) {
                    ans += 12.01 * count;
                } else if (let[i].equals("H")) {
                    ans += 1.008 * count;
                } else if (let[i].equals("O")) {
                    ans += 16.00 * count;
                } else if (let[i].equals("N")) {
                    ans += 14.01 * count;
                }
            }
            
            System.out.printf("%.3f\n", ans);
            t -= 1;
        }
    }
}
