import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

class Main {
    static Boolean is_box(Square a, Square b, Square c) {
        return
            a.h.equals(b.h) &&
            a.w.equals(c.h) &&
            b.w.equals(c.w);
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<Square> box = new ArrayList<Square>();
        HashMap<Square, Integer> map = new HashMap<Square, Integer>();
        Integer h = 0;
        Integer w = 0;
        Square delta = null;
        Square alpha = null;
        Boolean is_ok = false;
        while (stdin.hasNext()) {
            is_ok = true;
            box.clear();
            map.clear();
            for (Integer i = 0; i < 6; i += 1) {
                h = stdin.nextInt();
                w = stdin.nextInt();
                delta = new Square(Integer.min(h, w), Integer.max(h, w));
                if (!box.contains(delta)) {
                    box.add(delta);
                    map.put(delta, 1);
                } else {
                    map.put(delta, map.get(delta) + 1);
                }
            }
            if (box.size() > 3) {
                System.out.println("IMPOSSIBLE");
                continue;
            }
            if (box.size() == 1) { /* Cube */
                System.out.println("POSSIBLE");
                continue;
            }
            for (Integer i : map.values()) {
                if (i % 2 != 0) {
                    is_ok = false;
                    break;
                }
            }
            if (!is_ok) {
                System.out.println("IMPOSSIBLE");
                continue;
            }
            if (box.size() == 2) {
                /* Find which one duplicates */
                for (Square i : box) {
                    if (map.get(i) > 2) {
                        delta = i;
                    } else {
                        alpha = i;
                    }
                }
                if (is_box(delta, delta, alpha) ||
                    is_box(delta, alpha, delta) ||
                    is_box(alpha, delta, delta)) {
                    System.out.println("POSSIBLE");
                } else {
                    System.out.println("IMPOSSIBLE");
                }
            } else if (box.size() == 3) {
                if (is_box(box.get(0), box.get(1), box.get(2)) ||
                    is_box(box.get(0), box.get(2), box.get(1)) ||
                    is_box(box.get(1), box.get(0), box.get(2)) ||
                    is_box(box.get(1), box.get(2), box.get(0)) ||
                    is_box(box.get(2), box.get(0), box.get(1)) ||
                    is_box(box.get(2), box.get(1), box.get(0))) {
                    System.out.println("POSSIBLE");
                } else {
                    System.out.println("IMPOSSIBLE");
                }
            }
        }
    }
}

class Square {
    public Integer h;
    public Integer w;

    public Square(Integer h, Integer w) {
        this.h = h;
        this.w = w;
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        if (another == null || this.getClass() != another.getClass()) {
            return false;
        }
        return
            this.h.equals(((Square)another).h) &&
            this.w.equals(((Square)another).w);
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, w);
    }
}
