import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    static String rotate_clockwise_z(String dice) {
        ArrayList<Character> dice_list = new ArrayList<Character>();
        dice_list.add(dice.charAt(1 - 1));
        dice_list.add(dice.charAt(4 - 1));
        dice_list.add(dice.charAt(2 - 1));
        dice_list.add(dice.charAt(5 - 1));
        dice_list.add(dice.charAt(3 - 1));
        dice_list.add(dice.charAt(6 - 1));

        StringBuilder res = new StringBuilder();
        dice_list.forEach(i -> {res.append((char)i);});
        return res.toString();
    }

    static String rotate_clockwise_x(String dice) {
        ArrayList<Character> dice_list = new ArrayList<Character>();
        dice_list.add(dice.charAt(3 - 1));
        dice_list.add(dice.charAt(2 - 1));
        dice_list.add(dice.charAt(6 - 1));
        dice_list.add(dice.charAt(1 - 1));
        dice_list.add(dice.charAt(5 - 1));
        dice_list.add(dice.charAt(4 - 1));

        StringBuilder res = new StringBuilder();
        dice_list.forEach(i -> {res.append((char)i);});
        return res.toString();
    }

    static String rotate_clockwise_y(String dice) {
        ArrayList<Character> dice_list = new ArrayList<Character>();
        dice_list.add(dice.charAt(5 - 1));
        dice_list.add(dice.charAt(1 - 1));
        dice_list.add(dice.charAt(3 - 1));
        dice_list.add(dice.charAt(4 - 1));
        dice_list.add(dice.charAt(6 - 1));
        dice_list.add(dice.charAt(2 - 1));

        StringBuilder res = new StringBuilder();
        dice_list.forEach(i -> {res.append((char)i);});
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String a = null;
        String b = null;
        String delta = null;
        boolean is_equal = false;
        while (stdin.hasNext()) {
            delta = stdin.next();
            a = delta.substring(0, 6);
            b = delta.substring(6, 12);
            is_equal = false;
            for (int i = 0; i < 4; i += 1) {
                a = rotate_clockwise_z(a);
                for (int j = 0; j < 4; j += 1) {
                    a = rotate_clockwise_x(a);
                    for (int k = 0; k < 4; k += 1) {
                        a = rotate_clockwise_y(a);
                        if (a.equals(b)) {
                            is_equal = true;
                        }
                    }
                }
            }
            if (is_equal) {
                System.out.println("TRUE");
            } else {
                System.out.println("FALSE");
            }
        }
    }
}
