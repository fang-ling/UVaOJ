import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = stdin.nextInt();
        int n = 0;
        StringBuilder delta = null;
        int[] ans = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        while (t > 0) {
            delta = new StringBuilder();
            for (int i = 0; i < 10; i += 1) {
                ans[i] = 0;
            }
            n = stdin.nextInt();
            for (int i = 1; i <= n; i += 1) {
                delta.append(i);
            }
            for (int i = 0; i < delta.length(); i += 1) {
                ans[delta.charAt(i) - '0'] += 1;
            }
            for (int i = 0; i < ans.length; i += 1) {
                System.out.printf("%d", ans[i]);
                if (i != 9) {
                    System.out.print(" ");
                }
                
            }
            System.out.println();
            
            t -= 1;
        }
    }
}
