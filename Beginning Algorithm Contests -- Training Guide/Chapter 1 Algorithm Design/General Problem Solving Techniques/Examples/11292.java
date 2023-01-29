import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = 19358;
        int m = 12361;
        int cnt = 0;
        int current_head = 0;
        ArrayList<Integer> heads = new ArrayList<Integer>();
        ArrayList<Integer> knights = new ArrayList<Integer>();
        while (stdin.hasNext()) {
            current_head = 0;
            cnt = 0;
            heads.clear();
            knights.clear();

            n = stdin.nextInt();
            m = stdin.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            for (int i = 0; i < n; i += 1) {
                heads.add(stdin.nextInt());
            }
            for (int i = 0; i < m; i += 1) {
                knights.add(stdin.nextInt());
            }

            Collections.sort(heads);
            Collections.sort(knights);
            for (int k : knights) {
                if (k >= heads.get(current_head)) { /* Hire */
                    cnt += k;
                    if ((current_head += 1) == n) { /* No more heads */
                        break;
                    }
                }
            }
            if (current_head < n) {
                System.out.println("Loowater is doomed!");
            } else {
                System.out.println(cnt);
            }
        }
    }
}
