import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main{
    static void find_block(ArrayList<ArrayList<Integer>> table,
                           int block,
                           int[] res) {
        for (int i = 0; i < table.size(); i += 1) {
            for (int j = 0; j < table.get(i).size(); j += 1) {
                if (table.get(i).get(j) == block) {
                    res[0] = i;
                    res[1] = j;
                    return;
                }
            }
        }
    }

    static void clean_up(ArrayList<ArrayList<Integer>> table, int block) {
        int[] res = new int[2]; /* 0 -> table[x]; 1 -> table[x].get(y) */
        find_block(table, block, res);
        List<Integer> sub =
            table.get(res[0]).subList(res[1] + 1,
                                      table.get(res[0]).size());
        for (int i : sub) {
            table.get(i).add(i);
        }
        sub.clear();
    }

    static void move_on_top_of(ArrayList<ArrayList<Integer>> table,
                               int a,
                               int b) {
        int[] res = new int[2];
        find_block(table, a, res);
        List<Integer> sub =
            table.get(res[0]).subList(res[1],
                                      table.get(res[0]).size());
        find_block(table, b, res);
        ArrayList<Integer> buf = new ArrayList<Integer>();
        for (int i : sub) {
            buf.add(i);
        }
        sub.clear();
        table.get(res[0]).addAll(buf);
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = stdin.nextInt();
        int a = 0;
        int b = 0;
        String cmd = null;
        String pl = null;
        int[][] res = new int[2][2];
        ArrayList<ArrayList<Integer>> table =
            new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i += 1) {
            table.add(new ArrayList<Integer>());
            table.get(i).add(i);
        }
        while (stdin.hasNext()) {
            cmd = stdin.next();
            if (cmd.equals("quit")) {
                break;
            }
            a = stdin.nextInt();
            pl = stdin.next();
            b = stdin.nextInt();
            if (a == b) {
                continue;
            }
            find_block(table, a, res[0]);
            find_block(table, b, res[1]);
            if (res[0][0] == res[1][0]) {
                continue;
            }
            if (cmd.equals("move")) {
                if (pl.equals("onto")) {
                    clean_up(table, a);
                    clean_up(table, b);
                } else {
                    clean_up(table, a);
                }
                move_on_top_of(table, a, b);
            } else {
                if (pl.equals("onto")) {
                    clean_up(table, b);
                } else {

                }
                move_on_top_of(table, a, b);
            }
        }
        for (int i = 0; i < table.size(); i += 1) {
            System.out.printf("%d:", i);
            for (int j : table.get(i)) {
                System.out.printf(" %d", j);
            }
            System.out.println();
        }
    }
}
