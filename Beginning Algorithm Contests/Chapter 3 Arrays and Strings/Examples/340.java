import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int count_right_pos(ArrayList<Integer> target,
                               ArrayList<Integer> array) {
        int res = 0;
        for (int i = 0; i < target.size(); i += 1) {
            if (target.get(i) == array.get(i)) {
                res += 1;
            }
        }
        return res;
    }

    static int count_appreance(ArrayList<Integer> target,
                               ArrayList<Integer> array) {
        int res = 0;
        int res_t = 0;
        int res_a = 0;
        for (int i = 1; i <= 9; i += 1) {
            res_t = 0;
            res_a = 0;
            for (int j : target) {
                if (i == j) {
                    res_t += 1;
                }
            }
            for (int j : array) {
                if (i == j) {
                    res_a += 1;
                }
            }
            res += Integer.min(res_t, res_a);
        }
        return res;
    }
    
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<Integer> target = new ArrayList<Integer>();
        int n = 0;
        int game_cnt = 1;
        int delta = 0;
        while (stdin.hasNext()) {
            target.clear();
            n = stdin.nextInt();
            if (n == 0) {
                break;
            }
            for (int i = 0; i < n; i += 1) {
                target.add(stdin.nextInt());
            }
            System.out.println("Game " + game_cnt + ":");
            game_cnt += 1;
            while (true) {
                array.clear();
                for (int i = 0; i < n; i += 1) {
                    array.add(stdin.nextInt());
                }
                if (array.get(0) == 0) {
                    break;
                }
                delta = count_right_pos(target, array);
                System.out.println("    (" + delta + "," +
                                   (count_appreance(target, array) - delta) +
                                   ")");
            }
        }
    }
}
