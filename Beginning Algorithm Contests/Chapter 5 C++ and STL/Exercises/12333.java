import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

class Main {
    static boolean starts_with(BigInteger fib, BigInteger prefix) {
        while (fib.compareTo(BigInteger.ZERO) >= 0) {
            if (fib.compareTo(prefix) == 0) {
                return true;
            }
            fib = fib.divide(BigInteger.TEN);
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        BigInteger[] f = new BigInteger[100000 + 1];
        String[] f_nums = new String[100000 + 1];
        f_nums[0] = "1";
        f_nums[1] = "1";
        f[0] = BigInteger.ONE;
        f[1] = BigInteger.ONE;
        for (int i = 2; i <= 100000; i += 1) {
            f[i] = f[i - 1].add(f[i - 2]);
            //f_nums[i] = f[i].toString();
        }

        //for (int i = 2; i <= 100000; i += 1)
        //f_nums[i] = f[i].toString();

        //String delta = null;
        BigInteger delta = null;
        boolean contains = false;
        int t_case = stdin.nextInt();
        for (int k = 1; k <= t_case; k += 1) {
            delta = new BigInteger(stdin.next());

            contains = false;
            for (int i = 0; i <= 100000; i += 1) {
                //if (f_nums[i].startsWith(delta)) {
                if (starts_with(f[i], delta)) {
                    contains = true;
                    System.out.printf("Case #%d: %s\n", k, i);
                    break;
                }
            }
            if (!contains) {
                System.out.printf("Case #%d: -1\n", k);
            }
        }
    }
}
