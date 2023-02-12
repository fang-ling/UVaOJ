import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    static void solve(String[][] db, int n, int m) {
        String key = null;
        for (int i = 0; i < m; i += 1) {
            for (int j = i + 1; j < m; j += 1) {
                final HashMap<String, Integer> map =
                    new HashMap<String, Integer>();
                for (int k = 0; k < n; k += 1) {
                    key = db[k][i] + "," + db[k][j];
                    if (map.containsKey(key)) {
                        System.out.println("NO");
                        System.out.printf("%d %d\n", map.get(key) + 1, k + 1);
                        System.out.printf("%d %d\n", i + 1, j + 1);
                        return;
                    }
                    map.put(key, k);
                }
            }
        }
        System.out.println("YES");
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String[][] db = new String[10000][10];
        int n = 0;
        int m = 0;
        String delta = null;
        String[] alpha = null;
        while (stdin.hasNext()) {
            delta = stdin.nextLine();
            alpha = delta.split("\\s");
            n = Integer.parseInt(alpha[0]);
            m = Integer.parseInt(alpha[1]);
            for (int i = 0; i < n; i += 1) {
                db[i] = stdin.nextLine().split(",");
            }
            solve(db, n, m);
        }
    }
}
