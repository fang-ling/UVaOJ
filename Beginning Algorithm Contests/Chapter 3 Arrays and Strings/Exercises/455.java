import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = stdin.nextInt();
        String delta = null;
        StringBuilder alpha = null;
        boolean has_ans = false;
        for (; t > 0; t -= 1) {
            delta = stdin.next();
            if (delta.length() == 1) {
                System.out.println(1);
                if (t != 1) {
                    System.out.println();
                }
                continue;
            }
            for (int i = 1; i < delta.length(); i += 1) {
                has_ans = false;
                alpha = new StringBuilder();
                if (delta.length() % i != 0) {
                    continue;
                }
                alpha.append(delta.substring(0, i));
                for (int k = 1, len = delta.length() / i; k < len; k += 1) {
                    alpha.append(delta.substring(0, i));
                }
                if (alpha.toString().equals(delta)) {
                    has_ans = true;
                    System.out.println(i);
                    if (t != 1) {
                        System.out.println();
                    }
                    break;
                }
            }
            if (!has_ans) {
                System.out.println(delta.length());
                if (t != 1) {
                    System.out.println();
                }
            }
        }
    }
}
