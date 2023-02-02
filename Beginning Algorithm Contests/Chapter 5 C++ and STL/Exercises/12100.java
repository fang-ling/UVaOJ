import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int T = stdin.nextInt();
        int n = 0;
        int m = 0;
        final Job[] j = new Job[1];
        int ans = 0;
        LinkedList<Job> queue = new LinkedList<Job>();
        while ((T -= 1) >= 0) {
            queue.clear();

            n = stdin.nextInt();
            m = stdin.nextInt();
            for (int i = 0; i < n; i += 1) {
                queue.addLast(new Job(i, stdin.nextInt()));
            }
            ans = 0;
            while (true) {
                j[0] = queue.removeFirst();
                if (queue
                    .stream()
                    .filter(i -> i.priority > j[0].priority)
                    .collect(Collectors.toList()).size() > 0) {
                    queue.addLast(j[0]);
                } else {
                    if (j[0].id == m) {
                        break;
                    }
                    ans += 1;
                }
            }
            System.out.println(ans + 1);
        }
    }
}

class Job {
    int id;
    int priority;

    Job(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }
}
