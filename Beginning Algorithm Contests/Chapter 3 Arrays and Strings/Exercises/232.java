import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int m = 0;
        int n = 0;
        char[][] puzzle = new char[11][11];
        String delta = null;
        int index = 1;
        ArrayList<Word> h_words = new ArrayList<Word>();
        ArrayList<Word> v_words = new ArrayList<Word>();
        StringBuilder sb = null;
        int puzzle_cnt = 1;
        boolean is_first = true;
        while (stdin.hasNext()) {
            m = stdin.nextInt();
            if (m == 0) {
                break;
            }
            if (!is_first) System.out.println();
            is_first = false;
            n = stdin.nextInt();
            for (int i = 0; i < m; i += 1) {
                delta = stdin.next();
                for (int j = 0; j < n; j += 1) {
                    puzzle[i][j] = delta.charAt(j);
                }
            }
            /* Numbering */
            index = 1;
            h_words.clear();
            v_words.clear();
            for (int i = 0; i < m; i += 1) {
                for (int j = 0; j < n; j += 1) {
                    if (puzzle[i][j] != '*') { /* Non-black */
                        if (i < 1 || puzzle[i - 1][j] == '*') { /* V */
                            sb = new StringBuilder();
                            for (int k = i;
                                 k < m && puzzle[k][j] != '*';
                                 k += 1) {
                                sb.append(puzzle[k][j]);
                            }
                            v_words.add(new Word(index, sb.toString()));
                        }
                        if (j < 1 || puzzle[i][j - 1] == '*') { /* H */
                            sb = new StringBuilder();
                            for (int k = j;
                                 k < n && puzzle[i][k] != '*';
                                 k += 1) {
                                sb.append(puzzle[i][k]);
                            }
                            h_words.add(new Word(index, sb.toString()));
                        }
                        if ((i < 1 || puzzle[i - 1][j] == '*') ||
                            (j < 1 || puzzle[i][j - 1] == '*')) {
                            index += 1;
                        }
                    }
                }
            }
            System.out.printf("puzzle #%d:\n", puzzle_cnt);
            puzzle_cnt += 1;
            System.out.println("Across");
            for (Word i : h_words) {
                System.out.printf("%3d.%s\n", i.index, i.content);
            }
            System.out.println("Down");
            for (Word i : v_words) {
                System.out.printf("%3d.%s\n", i.index, i.content);
            }
        }
    }
}

class Word {
    public int index;
    public String content;

    public Word(int index, String content) {
        this.index = index;
        this.content = content;
    }
}
