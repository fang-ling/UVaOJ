import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        Integer n = 1;
        Integer k = null;
        Integer m = null;
        ArrayList<Integer> people = new ArrayList<Integer>();
        int cw = 0;
        int ccw = 0;
        int current_size = 0;
        while (stdin.hasNext()) {
            people.clear();

            n = stdin.nextInt();
            k = stdin.nextInt();
            m = stdin.nextInt();
            if (n == 0) {
                break;
            }

            current_size = n;
            for (Integer i = -1; i <= n; i += 1) {
                people.add(1);
            }
            cw = 0;
            ccw = n + 1;
            while (current_size > 0) {
                /* Clockwise */
                for (int i = 0; i < k; i += 1) {
                    cw += 1;
                    while (true) {
                        if (cw > n) {
                            cw -= n;
                        }
                        if (people.get(cw) != 0) {
                            break;
                        }
                        cw += 1;
                    }
                }
                /* Counterclockwise */
                for (int i = 0; i < m; i += 1) {
                    ccw -= 1;
                    while (true) {
                        if (ccw < 1) {
                            ccw += n;
                        }
                        if (people.get(ccw) != 0) {
                            break;
                        }
                        ccw -= 1;
                    }
                }
                people.set(cw, 0);
                people.set(ccw, 0);
                if (cw == ccw) {
                    System.out.printf("%3d", cw);
                    current_size -= 1;
                } else {
                    System.out.printf("%3d%3d", cw, ccw);
                    current_size -= 2;
                }
                if (current_size > 0) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
}
