import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int region_cnt = 0;
        int n = 0;
        int m = 0;
        int water = 0;
        int water_cnt = 0;
        double water_lever = 0;
        int delta = 0;
        int alpha = 0;
        ArrayList<Integer> regions = new ArrayList<Integer>();
        while ((n = stdin.nextInt()) != 0 && (m = stdin.nextInt()) != 0) {
            regions.clear();

            for (int i = 0, len = n * m; i < len; i += 1) {
                regions.add(stdin.nextInt());
            }
            water = stdin.nextInt();

            Collections.sort(regions);
            water_cnt = 0;
            water_lever = regions.get(0);
            for (int i = 0; i < regions.size(); i += 1) {
                if (water > 0) {
                    if (i != regions.size() - 1) { /* not the highest region */
                        delta = regions.get(i + 1) - regions.get(i);
                        alpha = 10 * (i + 1) * 10 * delta;
                        if (water < alpha) {
                            water_lever += (double)water / 10 / (10 * (i + 1));
                            water -= water;
                        } else {
                            water_lever += delta;
                            water -= alpha;
                        }
                    } else { /* hightest region with water */
                        water_lever += (double) water / 10 / (10 * (i + 1));
                        water -= water;
                    }
                }
            }
            for (int i : regions) {
                if (water_lever > i) {
                    water_cnt += 1;
                }
            }
            System.out.printf("Region %d\n", region_cnt += 1);
            System.out.printf("Water level is %.2f meters.\n", water_lever);
            System.out.printf("%.2f percent of the region is under water.\n\n",
                              (double) water_cnt / regions.size() * 100);
        }
    }
}
