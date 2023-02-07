import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
    static void enqueue(ArrayList<Integer> daa,
                        ArrayList<LinkedList<Integer>> queues,
                        LinkedList<Integer> team_queue,
                        int element) {
        if (queues.get(daa.get(element)).isEmpty()) {
            team_queue.addLast(daa.get(element));
        }
        queues.get(daa.get(element)).addLast(element);
    }

    static int dequeue(ArrayList<Integer> daa,
                       ArrayList<LinkedList<Integer>> queues,
                       LinkedList<Integer> team_queue) {
        int res = queues.get(team_queue.getFirst()).removeFirst();
        if (queues.get(team_queue.getFirst()).isEmpty()) {
            team_queue.removeFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = 0;
        int n = 0;
        int scenario = 0;
        String op = null;
        /* Direct Access Array */
        ArrayList<Integer> daa =
            new ArrayList<Integer>(Collections.nCopies(1000000, -1));
        ArrayList<LinkedList<Integer>> queues =
            new ArrayList<LinkedList<Integer>>();
        LinkedList<Integer> team_queue = new LinkedList<Integer>();
        while ((t = stdin.nextInt()) != 0) {
            Collections.fill(daa, -1);
            queues.clear();
            team_queue.clear();

            for (int i = 0; i < t; i += 1) {
                queues.add(new LinkedList<Integer>());
                n = stdin.nextInt();
                while((n -= 1) >= 0) {
                    daa.set(stdin.nextInt(), i);
                }
            }
            System.out.printf("Scenario #%d\n", scenario += 1);
            while (!(op = stdin.next()).equals("STOP")) {
                if (op.equals("ENQUEUE")) {
                    enqueue(daa, queues, team_queue, stdin.nextInt());
                } else if (op.equals("DEQUEUE")) {
                    System.out.println(dequeue(daa, queues, team_queue));
                }
            }
            System.out.println();
        }
    }
}
