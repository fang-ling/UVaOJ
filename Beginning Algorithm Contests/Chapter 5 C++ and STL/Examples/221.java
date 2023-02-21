import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int map_count = 0;
        int n = 0;
        int max_x = 0;
        int[] b = new int[5];
        boolean is_blocked = false;
        boolean is_first = true;
        ArrayList<Building> buildings = new ArrayList<Building>();
        ArrayList<ArrayList<Building>> seen =
            new ArrayList<ArrayList<Building>>();
        TreeSet<Building> ans = new TreeSet<Building>((b1, b2) -> {
                if (b1.x == b2.x) {
                    return Integer.compare(b1.y, b2.y);
                }
                return Integer.compare(b1.x, b2.x);
        });
        while ((n = stdin.nextInt()) != 0) {
            buildings.clear();
            seen.clear();
            ans.clear();

            if (!is_first) {
                System.out.println();
            }
            is_first = false;

            for (int id = 1; id <= n; id += 1) {
                for (int i = 0; i < 5; i += 1) {
                    b[i] = stdin.nextInt();
                }
                /* It's important to know that (x, y) is the southwest point,
                 * not the maximum x.
                 */
                max_x = Integer.max(max_x, b[0] + b[2]);
                buildings.add(new Building(id, b[0], b[1], b[2], b[4]));
            }
            for (int x = 0; x <= max_x; x += 1) {
                seen.add(new ArrayList<Building>());
                for (Building bb : buildings) {
                    if (x < bb.x || x >= bb.x + bb.w) { /* out of scope */
                        continue;
                    }
                    is_blocked = false;
                    for (Building j : buildings) {
                        //if (bb.equals(j)) { /* bb == j */
                        //continue;
                        //}
                        if (x < j.x || x >= j.x + j.w) { /* out of scope */
                            continue;
                        }
                        if (j.y < bb.y) { /* j is in front of bb */
                            if (j.h >= bb.h) { /* canot be see at x */
                                is_blocked = true;
                                break;
                            }
                        }
                    }
                    if (!is_blocked) {
                        seen.get(x).add(bb);
                    }
                }
            }
            for (ArrayList<Building> i : seen) {
                for (Building j : i) {
                    ans.add(j);
                }
            }
            System.out.printf("For map #%d, the visible buildings are " +
                              "numbered as follows:\n",
                              map_count += 1);
            for (Building i : ans) {
                System.out.printf("%d%s",
                                  i.id,
                                  i.equals(ans.last()) ? "" : " ");
            }
            System.out.println("");
        }
    }
}

class Building {
    int id;
    int x;
    int y;
    int w;
    int h;

    public Building(int id, int x, int y, int w, int h) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public boolean equals(Object another) {
        if (this.getClass() != another.getClass()) {
            return false;
        }
        Building b = (Building) another;
        return b.id == id;
    }
}
