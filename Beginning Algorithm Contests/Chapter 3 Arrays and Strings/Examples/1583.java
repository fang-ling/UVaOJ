import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Integer gen(int n) {
        int res = n;
        char[] digits = (n + "").toCharArray();
        for (char c : digits) {
            res += c - '0';
        }
        return res;
    }
    
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int delta = 0;
        for (int i = 0; i <= 100000; i += 1) {
            delta = gen(i);
            if (map.get(delta) == null) { /* empty */
                map.put(delta, i);
            } else { /* update if smaller */
                map.put(delta, Integer.min(i, map.get(delta)));
            }
        }
        
        int t = stdin.nextInt();
        while (t > 0) {
            delta = stdin.nextInt();
            System.out.println(map.get(delta) == null ? 0 : map.get(delta));
            t -= 1;
        }
    }
}
