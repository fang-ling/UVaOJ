import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int N = 0;
        ArrayList<Job> jobs = new ArrayList<Job>();
        int cnt = 0;
        int case_cnt = 0;
        int s = 0;
        while (stdin.hasNext()) {
            jobs.clear();

            N = stdin.nextInt();
            if (N == 0) {
                break;
            }
            for (int i = 0; i < N; i += 1) {
                jobs.add(new Job(stdin.nextInt(), stdin.nextInt()));
            }

            Collections.sort(jobs);
            s = 0;
            cnt = 0;
            for (int i = 0; i < N; i += 1) {
                s += jobs.get(i).bref;
                cnt = Integer.max(cnt, s + jobs.get(i).done);
            }
            System.out.printf("Case %d: %d\n", case_cnt += 1, cnt);
        }
    }
}

class Job implements Comparable<Job> {
    int bref;
    int done;

    public Job(int bref, int done) {
        this.bref = bref;
        this.done = done;
    }

    @Override
    public int compareTo(Job another) {
        if (done == another.done) {
            return Integer.compare(bref, another.bref);
        } else {
            return -1 * Integer.compare(done, another.done);
        }
    }
}
