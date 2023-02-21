import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<ArrayList<String>> words =
            new ArrayList<ArrayList<String>>();
        int cols = 0;
        while (stdin.hasNext()) {
            words.add(new ArrayList<String>());
            words
                .get(words.size() - 1)
                .addAll(Arrays.asList(stdin.nextLine().trim().split("\\s+")));
            cols = Integer.max(cols, words.get(words.size() - 1).size());
        }
        int[] lengths = new int[cols];
        Arrays.fill(lengths, 0);
        for (int i = 0; i < cols; i += 1) {
            for (ArrayList<String> row : words) {
                if (i < row.size()) {
                    lengths[i] = Integer.max(lengths[i], row.get(i).length());
                }
            }
        }
        for (ArrayList<String> row : words) {
            for (int i = 0; i < row.size(); i += 1) {
                System.out.print(row.get(i));
                if (i != row.size() - 1) { /* not last one */
                    System.out.print(" ");
                    for (int j = 0, len = lengths[i] - row.get(i).length();
                         j < len;
                         j += 1) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
}
