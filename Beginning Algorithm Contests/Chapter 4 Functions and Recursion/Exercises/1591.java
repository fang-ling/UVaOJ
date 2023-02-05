import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = 0;
        int s_p = 0;
        int s_q = 0;
        long delta = 0;
        long alpha = 0;
        long K = 0;
        int A = 0;
        int B = 0;
        while (stdin.hasNext()) {
            n = stdin.nextInt();
            s_p = stdin.nextInt();
            s_q = stdin.nextInt();

            alpha = (n - 1) * s_p;
            K = Long.MAX_VALUE;
            for (int i = 0; i < 32; i += 1) {
                for (int j = 0; j < 32; j += 1) {
                    /* Q'(n-1) = (P(n-1) + P(n-1) << A) >> B
                     * Q'(n-1) = ((n-1)*s_p + n-1*s_p << A) >> B
                     * Q'(n) = Q'(n-1) + s_q
                     */
                    delta = (alpha + (alpha << i) >> j) + s_q;
                    if (delta >= n * s_q && delta < K) {
                        K = delta;
                        A = i;
                        B = j;
                    }
                }
            }
            System.out.printf("%d %d %d\n", K, A, B);
        }
    }
}
