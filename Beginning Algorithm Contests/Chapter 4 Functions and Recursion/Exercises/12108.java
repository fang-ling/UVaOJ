import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int case_num = 0;
        ArrayList<Student> students = new ArrayList<Student>();
        boolean has_solution = false;
        Student s = null;
        while ((n = stdin.nextInt()) != 0) {
            students.clear();

            for (int i = 0; i < n; i += 1) {
                a = stdin.nextInt();
                b = stdin.nextInt();
                c = stdin.nextInt();
                students.add(new Student(a, b, c));
            }
            has_solution = false;
            for (int t = 0; t <= 100000; t += 1) {
                b = 0;
                for (int i = 0; i < students.size(); i += 1) {
                    s = students.get(i);
                    if ((t + s.c) % (s.a + s.b) <= s.a &&
                        (t + s.c) % (s.a + s.b) != 0) {
                        students.get(i).is_sleep = false;
                    } else if (!has_solution) {
                        students.get(i).is_sleep = true;
                    }
                }
                for (Student ss : students) {
                    b += ss.is_sleep ? 1 : 0;
                }
                if (b <= n / 2) {
                    has_solution = true;
                }
                if (b == 0) {
                    System.out.printf("Case %d: %d\n", case_num += 1, t + 1);
                    break;
                }
            }
            if (!has_solution) {
                System.out.printf("Case %d: -1\n", case_num += 1);
            }
        }
    }
}

class Student {
    int a;
    int b;
    int c;
    boolean is_sleep;

    Student(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
