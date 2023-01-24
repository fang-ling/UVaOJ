import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    static void delete_row(ArrayList<ArrayList<String>> tbl, int del) {
        tbl.remove(del);
    }

    static void delete_column(ArrayList<ArrayList<String>> tbl, int del) {
        for (ArrayList<String> i : tbl) {
            i.remove(del);
        }
    }

    static void insert_row(ArrayList<ArrayList<String>> tbl, int ins) {
        ArrayList<String> new_row = new ArrayList<>();
        for (int i = 0; i < tbl.get(0).size(); i += 1) {
            new_row.add("");
        }
        tbl.add(ins, new_row);
    }

    static void insert_column(ArrayList<ArrayList<String>> tbl, int ins) {
        for (ArrayList<String> i : tbl) {
            i.add(ins, "");
        }
    }

    static void swap_at(ArrayList<ArrayList<String>> tbl, Integer[] pos) {
        String delta = tbl.get(pos[0]).get(pos[1]);
        tbl.get(pos[0]).set(pos[1], tbl.get(pos[2]).get(pos[3]));
        tbl.get(pos[2]).set(pos[3], delta);
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int r = 0;
        int c = 0;
        int ops = 0;
        int A = 0;
        int q = 0;
        String delta = null;
        ArrayList<ArrayList<String>> tbl = new ArrayList<ArrayList<String>>();
        ArrayList<Integer> op_buf = new ArrayList<Integer>();
        boolean is_gone = false;
        int sheet_cnt = 1;
        boolean is_first = true;
        while (stdin.hasNext()) {
            for (ArrayList<String> i : tbl) {
                i.clear();
            }
            tbl.clear();
            r = stdin.nextInt();
            c = stdin.nextInt();
            if (r == 0 && c == 0) {
                break;
            }
            if (!is_first) {
                System.out.println();
            }
            is_first = false;
            /* Construct the table */
            for (int i = 0; i < r; i += 1) {
                tbl.add(new ArrayList<String>());
                for (int j = 0; j < c; j += 1) {
                    tbl.get(i).add(i + "," + j);
                }
            }

            ops = stdin.nextInt();
            for (int i = 0; i < ops; i += 1) {
                op_buf.clear();
                delta = stdin.next();
                if (delta.equals("DR")) {
                    A = stdin.nextInt();
                    for (int j = 0; j < A; j += 1) {
                        op_buf.add(stdin.nextInt() - 1);
                    }
                    Collections.sort(op_buf);
                    for (int j = 0; j < A; j += 1) {
                        delete_row(tbl, op_buf.get(j) - j);
                    }
                } else if (delta.equals("DC")) {
                    A = stdin.nextInt();
                    for (int j = 0; j < A; j += 1) {
                        op_buf.add(stdin.nextInt() - 1);
                    }
                    Collections.sort(op_buf);
                    for (int j = 0; j < A; j += 1) {
                        delete_column(tbl, op_buf.get(j) - j);
                    }
                } else if (delta.equals("IR")) {
                    A = stdin.nextInt();
                    for (int j = 0; j < A; j += 1) {
                        op_buf.add(stdin.nextInt() - 1);
                    }
                    Collections.sort(op_buf);
                    for (int j = 0; j < A; j += 1) {
                        insert_row(tbl, op_buf.get(j) + j);
                    }
                } else if (delta.equals("IC")) {
                    A = stdin.nextInt();
                    for (int j = 0; j < A; j += 1) {
                        op_buf.add(stdin.nextInt() - 1);
                    }
                    Collections.sort(op_buf);
                    for (int j = 0; j < A; j += 1) {
                        insert_column(tbl, op_buf.get(j) + j);
                    }
                } else if (delta.equals("EX")) {
                    for (int j = 0; j < 4; j += 1) {
                        op_buf.add(stdin.nextInt() - 1);
                    }
                    swap_at(tbl, op_buf.toArray(new Integer[0]));
                }
            }

            q = stdin.nextInt();
            System.out.printf("Spreadsheet #%d\n", sheet_cnt);
            sheet_cnt += 1;
            for (int i = 0; i < q; i += 1) {
                ops = stdin.nextInt() - 1;
                A = stdin.nextInt() - 1;
                delta = ops + "," + A;
                is_gone = true;
                for (int j = 0; j < tbl.size(); j += 1) {
                    for (int k = 0; k < tbl.get(j).size(); k += 1) {
                        if (tbl.get(j).get(k).equals(delta)) {
                            is_gone = false;
                            r = j;
                            c = k;
                            break;
                        }
                    }
                    if (is_gone == false) {
                        break;
                    }
                }
                if (is_gone) {
                    System.out.printf("Cell data in (%d,%d) GONE\n",
                                      ops + 1, A + 1);
                } else {
                    System.out.printf("Cell data in (%d,%d) moved" +
                                      " to (%d,%d)\n",
                                      ops + 1, A + 1, r + 1, c + 1);
                }
            }
        }
    }
}
