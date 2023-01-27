import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    static String normalize(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<String> words = new ArrayList<String>();
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        String delta = null;
        while (stdin.hasNext()) {
            delta = stdin.next();
            if (delta.equals("#")) {
                break;
            }

            words.add(delta);
        }

        for (String i : words) {
            delta = normalize(i);
            if (freq.get(delta) == null) { /* First time */
                freq.put(delta, 1);
            } else {
                freq.put(delta, freq.get(delta) + 1);
            }
        }
        Collections.sort(words);
        for (String i : words) {
            if (freq.get(normalize(i)) == 1) {
                System.out.println(i);
            }
        }
    }
}
