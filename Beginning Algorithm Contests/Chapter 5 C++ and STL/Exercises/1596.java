import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    static boolean is_int(String expr) {
		try {
			Integer.parseInt(expr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    static String get_between(String expr) {
		return expr.substring(expr.indexOf('[') + 1,
                              expr.lastIndexOf(']'));
	}

    static int evaluate(ArrayList<Array> arrays, String expr) {
		if (is_int(expr)) {
			return Integer.parseInt(expr);
		}

		int x = evaluate(arrays, get_between(expr));
		if (x < 0) {
			return x;
		}
		Array array = null;
		for (Array i : arrays) {
			if (i.name == expr.charAt(0)) {
				array = i;
				break;
			}
		}
		if (array == null || array.count == 0 || x >= array.count ||
            !array.storage.containsKey(x)) {
			return -1;
		}
		return array.storage.get(x);
	}

    static int evaluate_left(ArrayList<Array> arrays, String expr) {
        return evaluate(arrays, get_between(expr));
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<Array> arrays = new ArrayList<Array>();
        ArrayList<String> codes = new ArrayList<String>();
        String delta = null;
        String[] alpha = null;
        int left_index = 0;
        int right = 0;
        int line_num = 0;
        boolean is_printed = false;
        boolean is_finded = false;
        while (!(delta = stdin.next()).equals(".")) {
            codes.clear();
            arrays.clear();
            line_num = 0;
            is_printed = false;

            codes.add(delta);
            while (!(delta = stdin.next()).equals(".")) {
                codes.add(delta);
            }
            for (String beta : codes) {
                //System.out.println(beta);
                line_num += 1;
                if (!beta.contains("=")) { /* declaration */
                    arrays.add(new Array(beta.charAt(0),
                                         Integer.parseInt(get_between(beta))));
                } else { /* assignment */
                    alpha = beta.split("=");
                    //is_read = is_int(alpha[1])
                    left_index = evaluate_left(arrays, alpha[0]);
                    right = evaluate(arrays, alpha[1]);
                    //System.out.println(left_index);
                    if (left_index < 0 || right < 0) {
                        if (!is_printed) {
                            System.out.println(line_num);
                            is_printed = true;
                        }
                        continue;
                    }
                    is_finded = false;
                    for (Array i : arrays) {
                        if (i.name == alpha[0].charAt(0)) {
                            is_finded = true;
                            if (!is_printed &&
                                (i.count == 0 || i.count <= left_index)) {
                                System.out.println(line_num);
                                is_printed = true;
                                break;
                            }
                            i.storage.put(left_index,
                                          evaluate(arrays, alpha[1]));
                            break;
                        }
                    }
                    if (!is_finded) {
                        System.out.println(line_num);
                        is_printed = true;
                    }
                }
            }
            if (!is_printed) {
                System.out.println(0);
            }
            //System.out.println(".");
        }
    }
}

class Array {
    char name;
    int count;
    HashMap<Integer, Integer> storage;

    Array(char name, int count) {
        this.name = name;
        this.count = count;
        storage = new HashMap<Integer, Integer>();
    }
}
