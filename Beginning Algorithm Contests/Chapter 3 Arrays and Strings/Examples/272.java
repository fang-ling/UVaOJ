import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main272 {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        String str = null;
        Boolean isLeft = true;
        char[] delta = null;
        while (stdin.hasNext()) {
            str = stdin.nextLine();
            delta = str.toCharArray();
            for (Character c : delta) {
                if (c == '"') {
                    if (isLeft) {
                        System.out.print("``");
                    } else {
                        System.out.print("''");
                    }
                    isLeft = !isLeft;
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }
}
        
