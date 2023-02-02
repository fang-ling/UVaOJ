import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        TreeSet<String> words = new TreeSet<String>();
        TreeSet<String> ans = new TreeSet<String>();
        while (stdin.hasNext()) {
            words.add(stdin.next());
        }

        for (String i : words) {
            for (int j = 0; j < i.length(); j += 1) {
                if (words.contains(i.substring(0, j)) &&
                    words.contains(i.substring(j, i.length()))) {
                    ans.add(i);
                }
            }
        }
        ans.forEach(i -> System.out.println(i));
    }
}
