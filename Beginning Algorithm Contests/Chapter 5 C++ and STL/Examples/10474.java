import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = 0;
        int q = 0;
        int x = 0;
        int case_count = 1;
        int pos = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (stdin.hasNext()) {
            nums.clear();

            n = stdin.nextInt();
            q = stdin.nextInt();
            if (n == 0 && q == 0) {
                break;
            }

            for (int i = 0; i < n; i += 1) {
                nums.add(stdin.nextInt());
            }
            Collections.sort(nums);
            System.out.printf("CASE# %d:\n", case_count);
            case_count += 1;
            for (int i = 0; i < q; i += 1) {
                x = stdin.nextInt();
                pos = Collections.binarySearch(nums, x);
                if (pos < 0) { /* Not found */
                    System.out.printf("%d not found\n", x);
                } else {
                    while (pos > 0 && nums.get(pos - 1) == x) {
                        pos -= 1;
                    }
                    System.out.printf("%d found at %d\n", x, pos + 1);
                }
            }
        }
    }
}
