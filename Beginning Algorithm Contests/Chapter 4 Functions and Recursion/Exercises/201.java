import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    static int get_ptr_id(int i, int j, int n) {
        return i * n + j;
    }

    static boolean is_square(boolean[][] board, int pid, int n, int length) {
        /* Top */
        for (int l = 0; l < length; l += 1) {
            if (!board[pid + l][pid + l + 1]) {
                return false;
            }
        }
        /* Left */
        for (int l = 0; l < length; l += 1) {
            if (!board[pid + l * n][pid + l * n + n]) {
                return false;
            }
        }
        /* Down */
        pid += length * n;
        for (int l = 0; l < length; l += 1) {
            if (!board[pid + l][pid + l + 1]) {
                return false;
            }
        }
        /* Right */
        pid -= length * n;
        pid += length;
        for (int l = 0; l < length; l += 1) {
            if (!board[pid + l * n][pid + l * n + n]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        boolean[][] board = new boolean[121][121];
        int n = 0;
        int m = 0;
        int x = 0;
        int y = 0;
        int delta = 0;
        int problem = 0;
        int[] cnt = new int[10];
        String direction = null;
        boolean is_result = false;
        boolean is_first = true;
        while (stdin.hasNext()) {
            n = stdin.nextInt();
            m = stdin.nextInt();

            Arrays.fill(cnt, 0);
            for (int i = 0; i < (n + 2) * (n + 2); i += 1) {
                Arrays.fill(board[i], false);
            }

            for (int i = 0; i < m; i += 1) {
                direction = stdin.next();
                x = stdin.nextInt() - 1;
                y = stdin.nextInt() - 1;
                if (direction.equals("H")) {
                    board[get_ptr_id(x, y, n)][get_ptr_id(x, y + 1, n)] = true;
                    board[get_ptr_id(x, y + 1, n)][get_ptr_id(x, y, n)] = true;
                } else {
                    delta = x;
                    x = y;
                    y = delta;
                    board[get_ptr_id(x, y, n)][get_ptr_id(x + 1, y, n)] = true;
                    board[get_ptr_id(x + 1, y, n)][get_ptr_id(x, y, n)] = true;
                }
            }
            for (int i = 0; i < n * n; i += 1) {
                for (int l = 1; l <= n; l += 1) {
                    if (is_square(board, i, n, l)) {
                        cnt[l] += 1;
                    }
                }
            }

            if (!is_first) {
                System.out.print("\n**********************************\n\n");
            } else {
                is_first = false;
            }
            System.out.printf("Problem #%d\n\n", problem += 1);
            is_result = false;
            for (int i = 1; i <= n; i += 1) {
                if (cnt[i] != 0) {
                    is_result = true;
                }
            }
            if (is_result) {
                for (int i = 1; i <= n; i += 1) {
                    if (cnt[i] == 0) {
                        continue;
                    }
                    System.out.printf("%d square (s) of size %d\n", cnt[i], i);
                }
            } else {
                System.out.println("No completed squares can be found.");
            }
        }
    }
}
