import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = stdin.nextInt();
        String delta = null;
        char[] delta_array = null;
        ArrayList<Character> alpha = new ArrayList<Character>();
        StringBuilder beta = null;
        String min =
            "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" +
            "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" +
            "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        String gamma = null;
        
        while (t > 0) {
            delta = stdin.next();
            delta_array = delta.toCharArray();
            gamma = min;
            for (int i = 0; i < delta_array.length; i += 1) {
                alpha.clear();
                for (int k = i; k < delta_array.length; k += 1) {
                    alpha.add(delta_array[k]);
                }
                for (int k = 0; k < i; k += 1) {
                    alpha.add(delta_array[k]);
                }
                beta = new StringBuilder();
                for (char c : alpha) {
                    beta.append(c);
                }
                delta = beta.toString();
                gamma = gamma.compareTo(delta) < 0 ? gamma : delta;
            }
            System.out.println(gamma);
            t -= 1;
        }
    }
}
