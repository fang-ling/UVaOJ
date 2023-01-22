import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    static Double[] mag_to_dec = {
        0.5,
        0.75,
        0.875,
        0.9375,
        0.96875,
        0.984375,
        0.9921875,
        0.99609375,
        0.998046875,
        0.9990234375,
    };

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        /* Pre-compute all possible answers */
        Double delta = null;
        Integer exp = null;
        /* Answer in log scale */
        Double[][] answers = new Double[10][31];
        String alpha = null;
        for (int M = 0; M <= 9; M += 1) {
            for (int E = 1; E <= 30; E += 1) {
                delta = Math.log10(mag_to_dec[M]);
                exp = (1 << E) - 1;
                delta += exp * Math.log10(2);
                answers[M][E] = delta;
            }
        }

        String[] beta = null;
        final Double epsilon = 1e-6;
        while (stdin.hasNext()) {
            alpha = stdin.next();
            beta = alpha.split("e");
            delta = Double.parseDouble(beta[0]);
            exp = Integer.parseInt(beta[1]);
            if (exp.equals(0) && alpha.equals("0")) {
                break;
            }
            for (int M = 0; M <= 9; M += 1) {
                for (int E = 1; E <= 30; E += 1) {
                    if (Math.abs(answers[M][E] -
                                 (Math.log10(delta) + exp)) <= epsilon) {
                        System.out.printf("%d %d\n", M, E);
                        break;
                    }
                }
            }
        }
    }
}
