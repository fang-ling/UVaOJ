import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Main {
    static final String WELCOME =
        "Welcome to Student Performance Management System (SPMS).\n" +
        "\n" +
        "1 - Add\n" +
        "2 - Remove\n" +
        "3 - Query\n" +
        "4 - Show ranking\n" +
        "5 - Show Statistics\n" +
        "0 - Exit" +
        "\n";
    static final String OP1_MSG =
        "Please enter the SID, CID, name and four scores. Enter 0 to finish.";
    static final String OP2_MSG =
        "Please enter SID or name. Enter 0 to finish.";
    static final String OP4_MSG =
        "Showing the ranklist hurts students' self-esteem. Don't do that.";
    static final String OP5_MSG =
        "Please enter class ID, 0 for the whole statistics.";

    static void add_student(ArrayList<Student> db, Student s) {
        if (db.contains(s)) {
            System.out.println("Duplicated SID.");
        } else {
            db.add(s);
        }
    }

    static void remove_student(ArrayList<Student> db, String name) {
        int cnt = 0;
        for (int i = 0; i < db.size(); i += 1) {
            if (db.get(i).name.equals(name)) {
                cnt += 1;
                db.remove(i);
                i -= 1;
            }
        }
        System.out.printf("%d student(s) removed.\n", cnt);
    }

    static void remove_student(ArrayList<Student> db, long sid) {
        int cnt = 0;
        for (int i = 0; i < db.size(); i += 1) {
            if (db.get(i).sid == sid) {
                cnt += 1;
                db.remove(i);
                i -= 1;
            }
        }
        System.out.printf("%d student(s) removed.\n", cnt);
    }

    private static ArrayList<Integer> get_rank_list(ArrayList<Student> db) {
        ArrayList<Integer> all_score = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (Student i : db) {
            all_score.add(i.scores[0] + i.scores[1] +
                          i.scores[2] + i.scores[3]);
        }
        Collections.sort(all_score, Collections.reverseOrder());
        /* Biggest at front */
        for (int i = 0, len = all_score.get(0) + 1; i < len; i += 1) {
            res.add(0);
        }
        for (int i = 0; i < all_score.size(); i += 1) {
            if (res.get(all_score.get(i)) == 0) { /* First insert */
                res.set(all_score.get(i), i);
            }
        }
        return res;
    }

    static void select_student(ArrayList<Student> db, String name) {
        ArrayList<Integer> rank = get_rank_list(db);
        int total_score = 0;
        for (int i = 0; i < db.size(); i += 1) {
            if (db.get(i).name.equals(name)) {
                total_score = 0;
                for (int score : db.get(i).scores) {
                    total_score += score;
                }
                System.out.printf("%d %010d %d %s %d %d %d %d %d %.2f\n",
                                  rank.get(total_score) + 1,
                                  db.get(i).sid,
                                  db.get(i).cid,
                                  db.get(i).name,
                                  db.get(i).scores[0],
                                  db.get(i).scores[1],
                                  db.get(i).scores[2],
                                  db.get(i).scores[3],
                                  total_score,
                                  (double)total_score / 4.00000000000);
            }
        }
    }

    static void select_student(ArrayList<Student> db, long sid) {
        ArrayList<Integer> rank = get_rank_list(db);
        int total_score	= 0;
        for (int i = 0; i < db.size(); i += 1) {
            if (db.get(i).sid == sid) {
                total_score = 0;
                for (int score : db.get(i).scores) {
                    total_score	+= score;
                }
                System.out.printf("%d %010d %d %s %d %d %d %d %d %.2f\n",
                                  rank.get(total_score) + 1,
                                  db.get(i).sid,
                                  db.get(i).cid,
                                  db.get(i).name,
                                  db.get(i).scores[0],
                                  db.get(i).scores[1],
                                  db.get(i).scores[2],
                                  db.get(i).scores[3],
                                  total_score,
                                  (double)total_score / 4.00000000000);
            }
        }
    }

    static void stat_student(ArrayList<Student> db, int cid) {
        String[] subject =
            {"Chinese", "Mathematics", "English", "Programming", "Overall:"};
        int total = 0;
        int passed = 0;
        int pass_all = 0;
        int pass_3 = 0;
        int pass_2 = 0;
        int pass_1 = 0;
        int pass_0 = 0;
        int size = 0;
        for (int i = 0; i < 4; i += 1) {
            total = 0;
            passed = 0;
            size = 0;
            for (Student s : db) {
                if (cid == 0 || s.cid == cid) {
                    total += s.scores[i];
                    if (s.scores[i] >= 60) {
                        passed += 1;
                    }
                    size += 1;
                }
            }
            System.out.println(subject[i]);
            System.out.printf("Average Score: %.2f\n",
                              (double)total / size);
            System.out.printf("Number of passed students: %d\n", passed);
            System.out.printf("Number of failed students: %d\n",
                              size - passed);
            System.out.println();
        }
        /* Overall */
        int num_pass = 0;
        for (Student s : db) {
            num_pass = 0;
            if (!(cid == 0 || s.cid == cid)) {
                continue;
            }
            for (int i = 0; i < 4; i += 1) {

                if (s.scores[i] >= 60) {
                    num_pass += 1;
                }
            }
            if (num_pass == 4) {
                pass_all += 1;
            }
            if (num_pass >= 3) {
                pass_3 += 1;
            }
            if (num_pass >= 2) {
                pass_2 += 1;
            }
            if (num_pass >= 1) {
                pass_1 += 1;
            }
            if (num_pass == 0) {
                pass_0 += 1;
            }
        }
        System.out.println(subject[4]);
        System.out.printf("Number of students who passed all subjects: %d\n",
                          pass_all);
        System.out
            .printf("Number of students who passed 3 or more subjects: %d\n",
                    pass_3);
        System.out
            .printf("Number of students who passed 2 or more subjects: %d\n",
                    pass_2);
        System.out
            .printf("Number of students who passed 1 or more subjects: %d\n",
                    pass_1);
        System.out.printf("Number of students who failed all subjects: %d\n",
                          pass_0);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int op = 0;
        long sid = 0;
        String delta = null;
        int order = 0;
        ArrayList<Student> db = new ArrayList<Student>();
        System.out.println(WELCOME);
        while (stdin.hasNext()) {
            op = stdin.nextInt();
            if (op == 0) {
                break;
            } else if (op == 1) { /* Add */
                while (true) {
                    System.out.println(OP1_MSG);
                    sid = stdin.nextLong();
                    if (sid == 0) { /* End of OP1 */
                        break;
                    }
                    add_student(db, new Student(order,
                                                sid,
                                                stdin.nextInt(), /* cid */
                                                stdin.next(),    /* name */
                                                stdin.nextInt(), /* chi */
                                                stdin.nextInt(), /* mat */
                                                stdin.nextInt(), /* eng */
                                                stdin.nextInt()) /* pro */);
                    order += 1;
                }
            } else if (op == 2) { /* Remove */
                while (true) {
                    System.out.println(OP2_MSG);
                    delta = stdin.next();
                    if (delta.equals("0")) {
                        break;
                    }
                    if (Character.isDigit(delta.charAt(0))) { /* sid */
                        remove_student(db, Long.parseLong(delta));
                    } else { /* name */
                        remove_student(db, delta);
                    }
                }
            } else if (op == 3) { /* Select */
                while (true) {
                    System.out.println(OP2_MSG); /* Same as op2 */
                    delta = stdin.next();
                    if (delta.equals("0")) {
                        break;
                    }
                    if (Character.isDigit(delta.charAt(0))) { /* sid */
                        select_student(db, Long.parseLong(delta));
                    } else { /* name */
                        select_student(db, delta);
                    }
                }
            } else if (op == 4) { /* ranklist */
                System.out.println(OP4_MSG);
            } else if (op == 5) {
                System.out.println(OP5_MSG);
                stat_student(db, stdin.nextInt());
            } else if (op == 999) { /* Debug use */
                for (Student s : db) {
                    System.out.println(s);
                }
            }
            System.out.println(WELCOME);
        }
    }
}

class Student {
    public int order;
    public long sid;
    public int cid;
    public String name;
    public int[] scores;

    public Student(int order,
                   long sid, int cid, String name,
                   int c, int m, int e, int p) {
        this.order = order;
        this.sid = sid;
        this.cid = cid;
        this.name = new String(name);
        scores = new int[4];
        scores[0] = c;
        scores[1] = m;
        scores[2] = e;
        scores[3] = p;
    }

    public Student() {
        name = "";
        scores = new int[4];
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        if (another == null || this.getClass() != another.getClass()) {
            return false;
        }
        return this.sid == ((Student)another).sid;
    }

    /* Debug use */
    @Override
    public String toString() {
        return sid + " " + cid + " " + name + " " + Arrays.toString(scores);
    }
}
