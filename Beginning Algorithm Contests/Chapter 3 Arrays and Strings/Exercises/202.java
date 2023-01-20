import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int a = 0;
        int b = 0;
        int A = 0;
        int B = 0;
        int begin = 0;
        int end = 0;
        ArrayList<Integer> quo = new ArrayList<Integer>();
        ArrayList<Integer> rem = new ArrayList<Integer>();
        while (stdin.hasNext()) {
            rem.clear();
            quo.clear();
            a = stdin.nextInt();
            A = a;
            b = stdin.nextInt();
            B = b;
            /* When remainder repeats, quotient repeats */
            while (a / b > 0) {
                a %= b;
            }
            if (a == 0) { /* Special case like 7 / 7 */
                System.out.printf("%d/%d = %d.(0)\n", A, B, A / B);
                System.out.println(
                             "   1 = number of digits in repeating cycle\n");
                continue;
            }
            rem.add(a);
            while (true) {
                a *= 10;
                quo.add(a / b);
                a %= b;
                if (a == 0) {
                    begin = rem.size();
                    end = begin;
                    break;
                }
                if (rem.contains(a)) {
                    begin = rem.indexOf(a);
                    end = rem.size() - 1;
                    break;
                }
                rem.add(a);
            }
            System.out.printf("%d/%d = %d.", A, B, A / B);
            for (int i = 0; i < quo.size(); i += 1) {
                if (i == begin) {
                    System.out.print("(");
                }
                System.out.print(quo.get(i));
                if (i == end && end <= 49) {
                    System.out.println(")");
                    break;
                }
                if (i >= 49) {
                    System.out.println("...)");
                    break;
                }
            }
            if (begin == end && begin >= quo.size()) {
                System.out.println("(0)");
            }
            System.out.printf(
                            "   %d = number of digits in repeating cycle\n\n",
                            end - begin + 1);
        }
    }
}
