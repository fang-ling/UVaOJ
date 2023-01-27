import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        TreeSet<String> set = new TreeSet<String>();
        StringBuilder buf = new StringBuilder();
        while (stdin.hasNext()) {
            buf.append(stdin.next().toLowerCase().replaceAll("[^a-z]", " "));
            buf.append(' ');
        }
        String[] words = buf.toString().split("\\s+");
        for (String i : words) {
            set.add(i);
        }
        for (String i : set) {
            System.out.println(i);
        }
    }
}
