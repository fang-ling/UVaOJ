import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String header = null;
        String delta = null;
        StringBuilder sb = new StringBuilder();
        char[][] tbl = new char[8][1 << 8];
        int k = 0;
        int val = 0;
        ArrayList<String> input = new ArrayList<String>();
        while (stdin.hasNext()) {
            input.add(stdin.nextLine());
        }
        boolean is_header = false;
        for (int i = 0; i < input.size(); i += 1) {
            is_header = false;
            for (int j = 0; j < input.get(i).length(); j += 1) {
                if (input.get(i).charAt(j) != '0' &&
                    input.get(i).charAt(j) != '1') {
                    is_header = true;
                    break;
                }
            }
            if (is_header) {
                sb.append("\n");
                sb.append(input.get(i));
                sb.append("\n");
            } else {
                sb.append(input.get(i));
            }
        }
        String[] format = sb.toString().split("\n");
        for (int ii = 1; ii < format.length; ii += 2) {
            sb = new StringBuilder(format[ii + 1]);
            header = format[ii];
            /* Construct key table */
            k = 0;
            for (int i = 1; i <= 8; i += 1) {
                for (int j = 0, len = (1 << i) - 1; j < len; j += 1) {
                    if (k >= header.length()) {
                        break;
                    }
                    tbl[i][j] = header.charAt(k);
                    k += 1;
                }
            }
            /* Decode */
            while (sb.length() > 0) {
                k = Integer.parseInt(sb.charAt(0) + "" +
                                     (sb.charAt(1) + "") +
                                     (sb.charAt(2) + ""), 2);
                if (k == 0) { /* EOS */
                    break;
                }
                sb = sb.delete(0, 3);
                while (sb.length() > 0) {
                    delta = "";
                    for (int i = 0; i < k; i += 1) {
                        delta += sb.charAt(i);
                    }
                    sb = sb.delete(0, k);
                    val = Integer.parseInt(delta, 2);
                    /* Check all ones */
                    if (delta.replace("1", "").length() == 0) {
                        break;
                    }
                    System.out.print(tbl[k][val]);
                }
            }
            System.out.println();
        }
    }
}
