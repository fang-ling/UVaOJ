import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static long ipv4_to_int32(String ip_addr) {
        long ip = 0;
        String[] parts = ip_addr.split("\\.");
        for (int i = 0; i < 4; i += 1) {
            ip += Integer.parseInt(parts[i]) << (24 - 8 * i);
        }
        return ip;
    }

    static long low_32bit(long a) {
        return a & 0xFFFFFFFFL;
    }

    static boolean is_in_same_network(long ip1, long ip2, long mask) {
        return low_32bit(ip1 & mask) == low_32bit(ip2 & mask);
    }

    static String int32_to_ipv4(long ip) {
        long mask = 255;
        String res = "";
        res += (ip >> 24) & mask;
        res += ".";
        res += (ip >> 16) & mask;
        res += ".";
        res += (ip >> 8) & mask;
        res += ".";
        res += ip & mask;
        return res;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int m = 0;
        long mask = 0;
        ArrayList<Long> addrs = new ArrayList<Long>();
        boolean is_ok = false;
        boolean has_result = false;
        while (stdin.hasNext()) {
            addrs.clear();

            m = stdin.nextInt();
            for (int i = 0; i < m; i += 1) {
                addrs.add(ipv4_to_int32(stdin.next()));
            }
            has_result = false;
            for (int bit = 0; bit < 32; bit += 1) {
                mask = 0xffffffffl << bit;
                is_ok = true;
                for (int i = 1; i < m; i += 1) {
                    if (!is_in_same_network(addrs.get(0), addrs.get(i), mask))
                        is_ok = false;
                }
                if (is_ok) {
                    System.out.println(int32_to_ipv4(addrs.get(0) & mask));
                    System.out.println(int32_to_ipv4(mask));
                    has_result = true;
                    break;
                }
            }
            if (!has_result) {
                System.out.println("0.0.0.0");
                System.out.println("0.0.0.0");
            }
        }
    }
}
