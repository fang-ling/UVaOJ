import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    static boolean is_available(ArrayList<String> stripe) {
        for (String i : stripe) {
            if (i.contains("x")) {
                return false;
            }
        }
        return true;
    }

    static String to_string(ArrayList<ArrayList<String>> stripes,
                            int d, int s) {
        StringBuilder res = new StringBuilder();
        ArrayList<String> delta = new ArrayList<String>();
        for (int i = 0; i < stripes.size(); i += 1) {
            for (int j = 0; j < stripes.get(i).size(); j += 1) {
                if (i % d == j) { /* Parity data */
                  continue;
                }
                res.append(stripes.get(i).get(j));
            }
        }
        while (res.length() % 4 != 0) {
            res.append('0');
        }
        StringBuilder res2 = new StringBuilder();
        for (int i = 0, len = res.length() / 4; i < len; i += 1) {
            s = Integer.parseInt(res.substring(i * 4, (i + 1) * 4), 2);
            res2.append(Integer.toHexString(s));
        }
        return res2.toString().toUpperCase();
    }

    static boolean recovery(ArrayList<String> stripe, int parity, int s) {
        int x = 0; /* p ^ 0 = p */
        if (is_available(stripe)) {
            for (int i = 0; i < s; i += 1) {
                x = 0;
                for (String j : stripe) {
                    x ^= j.charAt(i) - '0';
                }
                if (x != parity) {
                    return false;
                }
            }
        }
        int unavailable_cnt = 0;
        for (int i = 0; i < s; i += 1) {
            unavailable_cnt = 0;
            for (String j : stripe) {
                if (j.charAt(i) == 'x') {
                    unavailable_cnt += 1;
                }
            }
            if (unavailable_cnt > 1) {
                return false;
            }
        }
        /* Start recovery */
        int x_pos = 0;
        StringBuilder sb = null;
        for (int i = 0; i < s; i += 1) {
            x = 0; /* p ^ 0 = p */
            x_pos = -1;
            for (int j = 0; j < stripe.size(); j += 1) {
                if (stripe.get(j).charAt(i) != 'x') {
                    x ^= stripe.get(j).charAt(i) - '0';
                } else if (stripe.get(j).charAt(i) == 'x') {
                    x_pos = j;
                }
            }
            if (x_pos == -1) {
                if (x != parity) {
                    return false;
                }
                continue;
            }
            x ^= parity;
            sb = new StringBuilder(stripe.get(x_pos));
            sb.setCharAt(i, (char)(x + '0'));
            stripe.set(x_pos, sb.toString());
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int disk_count = 0;
        int d = 0;
        int s = 0;
        int b = 0;
        int is_even = 0;
        ArrayList<ArrayList<String>> disks =
            new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> stripes =
            new ArrayList<ArrayList<String>>();
        String[] delta = null;
        int status = 0; /* 0: fail; 1: recoverable; 2: ok */
        while (stdin.hasNext()) {
            d = stdin.nextInt();
            if (d == 0) {
                break;
            }
            s = stdin.nextInt();
            b = stdin.nextInt();
            is_even = stdin.next().equals("E") ? 0 : 1;

            /* init */
            disks.clear();
            stripes.clear();

            for (int i = 0; i < d; i += 1) {
                delta = stdin.next().split("(?<=\\G.{" + s + "})");
                disks.add(new ArrayList<String>(Arrays.asList(delta)));
            }
            for (int i = 0; i < b; i += 1) {
                stripes.add(new ArrayList<String>());
                for (int j = 0; j < d; j += 1) {
                    stripes.get(i).add(disks.get(j).get(i));
                }
            }

            status = 2;
            for (ArrayList<String> i : stripes) {
                if (!recovery(i, is_even, s)) {
                    status = 0;
                }
            }
            disk_count += 1;
            if (status == 0) {
                System.out.printf("Disk set %d is invalid.\n", disk_count);
            } else {
                System.out.printf("Disk set %d is valid, contents are: %s\n",
                                  disk_count, to_string(stripes, d, s));
            }
        }
    }
}
