import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static String encode(String plain, String[] tbl) {
        StringBuilder morse = new StringBuilder();
        plain.chars().forEach(c -> morse.append(tbl[c]));
        return morse.toString();
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String[] tbl = new String[200];
        TreeMap<String, ArrayList<String>> dict =
            new TreeMap<String, ArrayList<String>>();
        String C = null;
        String cipher = null;
        int min_diff = Integer.MAX_VALUE;
        int delta = 0;
        while (true) {
            C = stdin.next();
            if (C.equals("*")) {
                break;
            }
            tbl[C.charAt(0)] = stdin.next();
        }
        while (true) {
            C = stdin.next();
            if (C.equals("*")) {
                break;
            }
            cipher = encode(C, tbl);
            if (!dict.containsKey(cipher)) {
                dict.put(cipher, new ArrayList<String>());
            }
            dict.get(cipher).add(C);
        }
        while (true) {
            C = stdin.next();
            if (C.equals("*")) {
                break;
            }
            if (dict.containsKey(C)) { /* Exists */
                System.out.print(dict.get(C).get(0));
                if (dict.get(C).size() > 1) {
                    System.out.println("!");
                } else {
                    System.out.println();
                }
            } else {
                cipher = "";
                min_diff = Integer.MAX_VALUE;
                for (Entry<String, ArrayList<String>> i : dict.entrySet()) {
                    if (i.getKey().startsWith(C) || C.startsWith(i.getKey())) {
                        delta = Math.abs(i.getKey().length() - C.length());
                        if (delta < min_diff) {
                            min_diff = delta;
                            cipher = i.getValue().get(0);
                        }
                    }
                }
                System.out.println(cipher + "?");
            }
        }
    }
}
