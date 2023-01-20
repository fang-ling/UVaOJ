import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String s = null;
        String t = null;
        LinkedList<Character> queue = new LinkedList<Character>();
        while (stdin.hasNext()) {
            s = stdin.next();
            t = stdin.next();

            queue.clear();
            for (int i = 0; i < s.length(); i += 1) {
                queue.addLast(s.charAt(i));
            }

            for (int i = 0; i < t.length() && !queue.isEmpty(); i += 1) {
                if (queue.getFirst() == t.charAt(i)) {
                    queue.removeFirst();
                }
            }
            System.out.println(queue.isEmpty() ? "Yes" : "No");
        }
    }
}
