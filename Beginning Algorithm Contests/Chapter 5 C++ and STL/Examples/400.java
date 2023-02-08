import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    static String add_extra_space(String file, int col) {
        StringBuilder sb = new StringBuilder(file);
        for (int i = file.length(); i < col; i += 1) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = 0;
        int m = 0;
        int cols = 0;
        int rows = 0;
        String delta = null;
        ArrayList<String> files = new ArrayList<String>();
        while (stdin.hasNext()) {
            files.clear();

            n = stdin.nextInt();
            m = 0;
            for (int i = 0; i < n; i += 1) {
                delta = stdin.next();
                m = Integer.max(m, delta.length());
                files.add(delta);
            }
            Collections.sort(files);
            cols = 0;
            while (cols * (m + 2) + m <= 60) {
                cols += 1;
            }
            rows = n / cols;
            if (rows * cols < n) {
                rows += 1;
            }
            System.out.println("------------------------------" +
                               "------------------------------");
            for (int i = 0; i < rows; i += 1) {
                for (int j = 0; j < cols; j += 1) {
                    n = j * rows + i; /* reuse n */
                    if (n >= files.size()) {
                        break;
                    }
                    if ((j + 1) * rows + i >= files.size()) { /* last col */
                        System.out.print(add_extra_space(files.get(n), m));
                    } else {
                        System.out.print(add_extra_space(files.get(n), m + 2));
                    }
                }
                System.out.println();
            }
        }
    }
}
