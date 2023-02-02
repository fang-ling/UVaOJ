import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<Integer> origin = new ArrayList<Integer>();
        ArrayList<Integer> foreign = new ArrayList<Integer>();
        int n = 0;
        while (stdin.hasNext()) {
            if ((n = stdin.nextInt()) == 0) {
                break;
            }

            origin.clear();
            foreign.clear();
            while ((n -= 1) >= 0) {
                origin.add(stdin.nextInt());
                foreign.add(stdin.nextInt());
            }
            Collections.sort(origin);
            Collections.sort(foreign);
            System.out.println(origin.equals(foreign) ? "YES" : "NO");
        }
    }
}
