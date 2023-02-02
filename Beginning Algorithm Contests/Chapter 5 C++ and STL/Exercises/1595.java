import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Main {
    static boolean is_symmetric(ArrayList<Point> points) {
        /* Find 2 points with minimum / maximum x values */
        int max = -1;
        for (int i = points.size() - 1; i >= 0; i -= 1) {
            if (points.get(i).x == points.get(points.size() - 1).x &&
                points.get(i).y == points.get(0).y) {
                max = i;
                break;
            }
        }
        if (max == -1) {
            return false;
        }
        int sym_x_times_2 = points.get(0).x + points.get(max).x;
        for (Point p : points) {
            if (p.x * 2 == sym_x_times_2) { /* on the symmetry axis */
                /* Do nothing */
            } else {
                if (!points.contains(new Point(sym_x_times_2 - p.x, p.y))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int T = stdin.nextInt();
        int n = 0;
        ArrayList<Point> points = new ArrayList<Point>();
        while ((T -= 1) >= 0) {
            points.clear();

            n = stdin.nextInt();
            while ((n -= 1) >= 0) {
                points.add(new Point(stdin.nextInt(), stdin.nextInt()));
            }
            Collections.sort(points, (p1, p2) -> {
                    return p1.x == p2.x ?
                        Integer.compare(p1.y, p2.y) :
                        Integer.compare(p1.x, p2.x);
                });
            System.out.println(is_symmetric(points) ? "YES" : "NO");
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object another) {
        if (this == null || another == null) {
            return false;
        }
        if (this.getClass() != another.getClass()) {
            return false;
        }
        return this.x == ((Point)another).x && this.y == ((Point)another).y;
    }
}
