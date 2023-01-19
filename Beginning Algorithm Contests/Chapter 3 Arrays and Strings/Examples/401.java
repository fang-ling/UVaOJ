import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main401 {
    static char[] MIRROR_TABLE = {
        'A', ' ', ' ', ' ', '3',
        ' ', ' ', 'H', 'I', 'L',
        ' ', 'J', 'M', ' ', 'O',
        ' ', ' ', ' ', '2', 'T',
        'U', 'V', 'W', 'X', 'Y',
        '5'
    };
    static char[] MIRROR_NUM_TABLE = {
        ' ', '1', 'S', 'E', ' ',
        'Z', ' ', ' ', '8', ' '
    };
    
    static Boolean isPalindrome(String s) {
        StringBuilder reverse = new StringBuilder(s);
        return s.equals(reverse.reverse().toString());
    }

    static Boolean isMirror(String s) {
        char[] original = s.toCharArray();
        char[] mirror = s.toCharArray();
        int len = s.length() / 2;
        if (s.length() % 2 == 0) { /* even case */
            /* Doing nothing */
        } else { /* odd case */
            if (Character.isDigit(original[len])) {
                if (original[len] != MIRROR_NUM_TABLE[original[len] - '0']) {
                    return false;
                }
            } else {
                if (original[len] != MIRROR_TABLE[original[len] - 'A']) {
                    return false;
                }
            }
        }
        for (int i = 0; i < len; i += 1) {
            if (Character.isDigit(original[i])) {
                if (original[s.length() - 1 - i] !=
                    MIRROR_NUM_TABLE[original[i] - '0']) {
                    return false;
                }
            } else {
                if (original[s.length() - 1 - i] !=
                    MIRROR_TABLE[original[i] - 'A']) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        String line = null;
        Boolean isMirror = false;
        Boolean isPalindrome = false;
        while (stdin.hasNext()) {
            line = stdin.nextLine();
            isMirror = isMirror(line);
            isPalindrome = isPalindrome(line);
            System.out.print(line + " -- ");
            if (isMirror && isPalindrome) {
                System.out.println("is a mirrored palindrome.");
            } else if (isMirror && !isPalindrome) {
                System.out.println("is a mirrored string.");
            } else if (!isMirror && isPalindrome) {
                System.out.println("is a regular palindrome.");
            } else {
                System.out.println("is not a palindrome.");
            }
            System.out.println();
        }
    }
}
