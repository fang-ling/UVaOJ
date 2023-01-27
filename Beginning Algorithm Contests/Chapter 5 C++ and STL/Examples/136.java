import java.util.PriorityQueue;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        int[] primes = {2, 3, 5};
        TreeSet<Long> set = new TreeSet<Long>();
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        set.add(1l);
        pq.add(1l);

        long delta = 0;
        long beta = 0;
        for (int i = 0; i < 1500; i += 1) {
            delta = pq.poll();
            if (i == 1499) {
                System.out.printf("The 1500'th ugly number is %d.\n", delta);
            }
            for (int p : primes) {
                beta = delta * p;
                if (!set.contains(beta)) {
                    set.add(beta);
                    pq.add(beta);
                }
            }
        }
    }
}
