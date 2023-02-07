import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Map.Entry;

class Main {
    static HashMap<TreeSet<Integer>, Integer> ids;
    static HashMap<Integer, TreeSet<Integer>> sets;
    static int id = -1;

    static int get_id(TreeSet<Integer> x) {
        for (Entry<TreeSet<Integer>, Integer> i : ids.entrySet()) {
            if (i.getKey().equals(x)) {
                return i.getValue();
            }
        }
        id += 1;
        ids.put(x, id);
        sets.put(id, x);
        return id;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ids = new HashMap<TreeSet<Integer>, Integer>();
        sets = new HashMap<Integer, TreeSet<Integer>>();

        int T = stdin.nextInt();
        int N = 0;
        String op = null;
        TreeSet<Integer> a = null;
        TreeSet<Integer> b = null;
        TreeSet<Integer> c = null;
        Stack<Integer> st = new Stack<Integer>();
        while ((T -= 1) >= 0) {
            st.clear();
            ids.clear();
            sets.clear();

            N = stdin.nextInt();
            while ((N -= 1) >= 0) {
                op = stdin.next();
                if (op.equals("PUSH")) {
                    st.push(get_id(new TreeSet<Integer>()));
                } else if (op.equals("DUP")) {
                    st.push(st.peek());
                } else if (op.equals("UNION")) {
                    a = sets.get(st.pop());
                    b = sets.get(st.pop());
                    c = (TreeSet<Integer>) a.clone();
                    c.addAll(b);
                    a = c;
                    //a.addAll(b);
                    st.push(get_id(a));
                } else if (op.equals("INTERSECT")) {
                    a =	sets.get(st.pop());
                    b =	sets.get(st.pop());
                    c =	(TreeSet<Integer>) a.clone();
                    c.retainAll(b);
                    a = c;
                    st.push(get_id(a));
                } else if (op.equals("ADD")) {
                    a = sets.get(st.pop());
                    b = sets.get(st.pop());
                    c = (TreeSet<Integer>) b.clone();
                    c.add(get_id(a));
                    b = c;
                    st.push(get_id(b));
                }
                System.out.println(sets.get(st.peek()).size());
            }
            System.out.println("***");
        }
    }
}
