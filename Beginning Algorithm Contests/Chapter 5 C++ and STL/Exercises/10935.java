import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        LinkedList<Integer> list = new LinkedList<Integer>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int n = 0;
        while (stdin.hasNext()) {
            n = stdin.nextInt();
            if (n == 0) {
                break;
            }

            ans.clear();
            list.clear();
            for (int i = 1; i <= n; i += 1) {
                list.add(i);
            }

            while (list.size() >= 2) {
                ans.add(list.removeFirst());
                list.addLast(list.removeFirst());
            }
            System.out.print("Discarded cards:" +
                             ((ans.size() == 0) ? "" : " "));
            for (int i = 0; i < ans.size(); i += 1) {
                System.out.print(ans.get(i));
                System.out.print(i == ans.size() - 1 ? "" : ", ");
            }
            System.out.printf("\nRemaining card: %d\n", list.getFirst());
        }
    }
}
