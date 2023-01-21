import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String a = null;
        String b = null;
        String delta = null;
        ArrayList<Character> A = new ArrayList<Character>();
        ArrayList<Character> B = new ArrayList<Character>();
        Boolean is_ok = false;
        Integer ans1 = 0;
        Integer ans2 = 0;
        while (stdin.hasNext()) {
            A.clear();
            B.clear();
            b = stdin.next();
            a = stdin.next();

            for (int i = 0; i < a.length(); i += 1) {
                A.add(a.charAt(i));
            }
            for (int i = 0; i < b.length(); i += 1) {
                B.add(b.charAt(i));
            }
            /* B is the bottom one. */
            for (int i = 0; i < a.length(); i += 1) {
                B.add('0');
            }
            /* Try every possible starting position in b. */
            for (int j = 0; j <= b.length(); j += 1) {
                is_ok = true;
                for (int i = 0; i < A.size(); i += 1) {
                    if (A.get(i) - '0' + B.get(i + j) - '0' > 3) {
                        is_ok = false;
                        break;
                    }
                }
                if (is_ok) {
                    ans1 =
                        j + A.size() < b.length() ?
                        b.length() : j + A.size();
                    break;
                }
            }

            A.clear();
            B.clear();
            delta = a;
            a = b;
            b = delta;

            for (int i = 0; i < a.length(); i += 1) {
                A.add(a.charAt(i));
            }
            for (int i = 0; i < b.length(); i += 1) {
                B.add(b.charAt(i));
            }
            /* B is the bottom one. */
            for (int i = 0; i < a.length(); i += 1) {
                B.add('0');
            }
            /* Try every possible starting position in b. */
            for (int j = 0; j <= b.length(); j += 1) {
                is_ok = true;
                for (int i = 0; i < A.size(); i += 1) {
                    if (A.get(i) - '0' + B.get(i + j) - '0' > 3) {
                        is_ok = false;
                        break;
                    }
                }
                if (is_ok) {
                    ans2 =
                        j + A.size() < b.length() ?
                        b.length() : j + A.size();
                    break;
                }
            }
            System.out.println(Integer.min(ans1, ans2));
        }
    }
}
