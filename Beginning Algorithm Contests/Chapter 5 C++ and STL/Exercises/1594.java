import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    static void ducci(ArrayList<Integer> seq) {
        int old_first = seq.get(0);
        for (int i = 0; i < seq.size() - 1; i += 1) {
            seq.set(i, Math.abs(seq.get(i) - seq.get(i + 1)));
        }
        seq.set(seq.size() - 1, Math.abs(seq.get(seq.size() - 1) - old_first));
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int T = stdin.nextInt();
        int n = 0;
        ArrayList<Integer> seq = new ArrayList<Integer>();
        while ((T -= 1) >= 0) {
            seq.clear();

            n = stdin.nextInt();
            while ((n -= 1) >= 0) {
                seq.add(stdin.nextInt());
            }
            n = 1001;
            while ((n -= 1) >= 0) {
                ducci(seq);
            }
            if (seq
                .stream()
                .filter(i -> i != 0)
                .collect(Collectors.toList())
                .size() > 0) {
                System.out.println("LOOP");
            } else {
                System.out.println("ZERO");
            }
        }
    }
}
