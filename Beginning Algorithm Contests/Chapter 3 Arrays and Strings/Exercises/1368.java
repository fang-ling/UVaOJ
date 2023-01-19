import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Main {
    static char maximum_with_lexo_small(DNA_FREQ[] frequency) {
        Arrays.sort(frequency);
        return frequency[0].nucleotide;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        ArrayList<String> dna = new ArrayList<String>();

        int T = stdin.nextInt();
        int n = 0;
        int m = 0;
        int diff = 0;
        ArrayList<DNA_FREQ[]> frequency = new ArrayList<DNA_FREQ[]>();
        for (; T > 0; T -= 1) {
            dna.clear();
            frequency.clear();
            n = stdin.nextInt();
            m = stdin.nextInt();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i += 1) {
                dna.add(stdin.next());
            }

            for (int j = 0; j < m; j += 1) {
                DNA_FREQ[] freq = new DNA_FREQ[4];
                freq[0] = new DNA_FREQ('A', 0);
                freq[1] = new DNA_FREQ('T', 0);
                freq[2] = new DNA_FREQ('C', 0);;
                freq[3] = new DNA_FREQ('G', 0);;
                for (String s : dna) {
                    switch (s.charAt(j)) {
                    case 'A' : freq[0].cnt += 1; break;
                    case 'T' : freq[1].cnt += 1; break;
                    case 'C' : freq[2].cnt += 1; break;
                    case 'G' : freq[3].cnt += 1; break;
                    }
                }
                frequency.add(freq);
            }
            /* Construct the smallest sequence */
            for (int j = 0; j < m; j += 1) {
                sb.append(maximum_with_lexo_small(frequency.get(j)));
            }
            System.out.println(sb.toString());
            /* Calc diff */
            diff = 0;
            for (int i = 0; i < m; i += 1) {
                for (String s : dna) {
                    if (sb.charAt(i) != s.charAt(i)) {
                        diff += 1;
                    }
                }
            }
            System.out.println(diff);
        }
    }
}

class DNA_FREQ implements Comparable<DNA_FREQ> {
    public char nucleotide;
    public int cnt;

    public DNA_FREQ(char nucleotide, int cnt) {
        this.nucleotide = nucleotide;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(DNA_FREQ b) {
        if (this.cnt == b.cnt) {
            return ((Character)nucleotide).compareTo(b.nucleotide);
        } else {
            return ((Integer)cnt).compareTo(b.cnt) * -1;
        }
    }
}
