import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeSet;

class Main {
    static void get_available_position(char[][] board,
                                       boolean current_player,
                                       TreeSet<String> res) {
        char disk_color = current_player ? 'W' : 'B';
        char another_color = current_player ? 'B' : 'W';
        int pos_i = 0;
        int pos_j = 0;
        /* Find all disks with same color */
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (board[i][j] == disk_color) {
                    /* Go left */
                    pos_i = i;
                    pos_j = j;
                    for (int k = j - 1; k >= 0; k -= 1) {
                        if (board[i][k] == another_color) {
                            pos_j = k;
                        } else {
                            break;
                        }
                    }
                    pos_j -= 1;
                    if (pos_j == j - 1 || pos_j < 0) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                    /* Go right */
                    pos_i = i;
                    pos_j = j;
                    for (int k = j + 1; k < 8; k += 1) {
                        if (board[i][k] == another_color) {
                            pos_j = k;
                        } else {
                            break;
                        }
                    }
                    pos_j += 1;
                    if (pos_j == j + 1 || pos_j > 7) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                    /* Go up */
                    pos_i = i;
                    pos_j = j;
                    for (int k = i - 1; k >= 0; k -= 1) {
                        if (board[k][j] == another_color) {
                            pos_i = k;
                        } else {
                            break;
                        }
                    }
                    pos_i -= 1;
                    if (pos_i == i - 1 || pos_i < 0) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                    /* Go down */
                    pos_i = i;
                    pos_j = j;
                    for (int k = i + 1; k < 8; k += 1) {
                        if (board[k][j] == another_color) {
                            pos_i = k;
                        } else {
                            break;
                        }
                    }
                    pos_i += 1;
                    if (pos_i == i + 1 || pos_i > 7) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                    /* Go left up */
                    pos_i = i;
                    pos_j = j;
                    for (int k = i - 1, l = j - 1;
                         k >= 0 && l >= 0;
                         k -= 1, l -= 1) {
                        if (board[k][l] == another_color) {
                            pos_i = k;
                            pos_j = l;
                        } else {
                            break;
                        }
                    }
                    pos_i -= 1;
                    pos_j -= 1;
                    if (pos_i == i - 1 || pos_j == j - 1 ||
                        pos_i < 0 || pos_j < 0) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                    /* Go left down */
                    pos_i = i;
                    pos_j = j;
                    for (int k = i + 1, l = j - 1;
                         k < 8 && l >= 0;
                         k += 1, l -= 1) {
                        if (board[k][l] == another_color) {
                            pos_i = k;
                            pos_j = l;
                        } else {
                            break;
                        }
                    }
                    pos_i += 1;
                    pos_j -= 1;
                    if (pos_i == i + 1 || pos_j == j - 1 ||
                        pos_i > 7 || pos_j < 0) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                    /* Go right up */
                    pos_i = i;
                    pos_j = j;
                    for (int k = i - 1, l = j + 1;
                         k >= 0 && l < 8;
                         k -= 1, l += 1) {
                        if (board[k][l] == another_color) {
                            pos_i = k;
                            pos_j = l;
                        } else {
                            break;
                        }
                    }
                    pos_i -= 1;
                    pos_j += 1;
                    if (pos_i == i - 1 || pos_j == j + 1 ||
                        pos_i < 0 || pos_j > 7) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                    /* Go right down */
                    pos_i = i;
                    pos_j = j;
                    for (int k = i + 1, l = j + 1;
                         k < 8 && l < 8;
                         k += 1, l += 1) {
                        if (board[k][l] == another_color) {
                            pos_i = k;
                            pos_j = l;
                        } else {
                            break;
                        }
                    }
                    pos_i += 1;
                    pos_j += 1;
                    if (pos_i == i + 1 || pos_j == j + 1 ||
                        pos_i > 7 || pos_j > 7) {
                        /* No black found in this direction,
                         * or out of board.
                         */
                    } else {
                        if (board[pos_i][pos_j] == '-')
                        res.add("(" + (pos_i + 1) + "," + (pos_j + 1) + ")");
                    }
                }
            }
        }
    }

    static void make_a_move(char[][] board,
                            boolean current_player,
                            int r,
                            int c,
                            int[] cnt) {
        char disk_color = current_player ? 'W' : 'B';
        board[r][c] = disk_color;
        /* Find up */
        int pos_i = r;
        int pos_j = c;
        for (int k = r - 1; k >= 0; k -= 1) {
            if (board[k][pos_j] == disk_color) {
                for (int i = r - 1; i > k; i -= 1) {
                    board[i][pos_j] = disk_color;
                }
                break;
            } else if (board[k][pos_j] == '-') {
                break;
            }
        }
        /* Find down */
        pos_i = r;
        pos_j = c;
        for (int k = r + 1; k < 8; k += 1) {
            if (board[k][pos_j] == disk_color) {
                for (int i = r + 1; i < k; i += 1) {
                    board[i][pos_j] = disk_color;
                }
                break;
            } else if (board[k][pos_j] == '-') {
                break;
            }
        }
        /* Find left */
        pos_i = r;
        pos_j = c;
        for (int k = c - 1; k >= 0; k -= 1) {
            if (board[pos_i][k] == disk_color) {
                for (int i = c - 1; i > k; i -= 1) {
                    board[pos_i][i] = disk_color;
                }
                break;
            } else if (board[pos_i][k] == '-') {
                break;
            }
        }
        /* Find right */
        pos_i = r;
        pos_j = c;
        for (int k = c + 1; k < 8; k += 1) {
            if (board[pos_i][k] == disk_color) {
                for (int i = c + 1; i < k; i += 1) {
                    board[pos_i][i] = disk_color;
                }
                break;
            } else if (board[pos_i][k] == '-') {
                break;
            }
        }
        /* Find left up */
        for (int k = r - 1, j = c - 1; k >= 0 && j >= 0; k -= 1, j -= 1) {
            if (board[k][j] == disk_color) {
                for (int i = r - 1, l = c - 1;
                     i > k && l > j;
                     i -= 1, l -= 1) {
                    board[i][l] = disk_color;
                }
                break;
            } else if (board[k][j] == '-') {
                break;
            }
        }
        /* Find left down */
        for (int k = r + 1, j = c - 1; k < 8 && j >= 0; k += 1, j -= 1) {
            if (board[k][j] == disk_color) {
                for (int i = r + 1, l = c - 1;
                     i < k && l > j;
                     i += 1, l -= 1) {
                    board[i][l] = disk_color;
                }
                break;
            } else if (board[k][j] == '-') {
                break;
            }
        }
        /* Find right up */
        for (int k = r - 1, j = c + 1; k >= 0 && j < 8; k -= 1, j += 1) {
            if (board[k][j] == disk_color) {
                for (int i = r - 1, l = c + 1;
                     i > k && l < j;
                     i -= 1, l += 1) {
                    board[i][l] = disk_color;
                }
                break;
            } else if (board[k][j] == '-') {
                break;
            }
        }
        /* Find right down */
        for (int k = r + 1, j = c + 1; k < 8 && j < 8; k += 1, j += 1) {
            if (board[k][j] == disk_color) {
                for (int i = r + 1, l = c + 1;
                     i < k && l < j;
                     i += 1, l += 1) {
                    board[i][l] = disk_color;
                }
                break;
            } else if (board[k][j] == '-') {
                break;
            }
        }
        /* count color */
        cnt[0] = 0;
        cnt[1] = 0;
        for (char[] i : board) {
            for (char cc : i) {
                if (cc == 'W') {
                    cnt[1] += 1;
                } else if (cc == 'B') {
                    cnt[0] += 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        char[][] board = new char[8][8];
        int T = stdin.nextInt();
        boolean current_player = true; /* true: W;  false: B */
        String op = null;
        TreeSet<String> position = new TreeSet<String>();
        String[] delta = null;
        int[] cnt = new int[2];
        while ((T -= 1) >= 0) {
            for (int i = 0; i < 8; i += 1) {
                board[i] = stdin.next().toCharArray();
            }
            current_player = stdin.next().equals("W");
            while (true) {
                position.clear();
                op = stdin.next();
                if (op.equals("Q")) {
                    break;
                } else if (op.equals("L")) {
                    get_available_position(board, current_player, position);
                    if (position.size() > 0) {
                        delta = new String[position.size()];
                        position.toArray(delta);
                        for (int i = 0; i < delta.length; i += 1) {
                            System.out.print(delta[i]);
                            if (i != delta.length - 1) {
                                System.out.print(' ');
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println("No legal move.");
                    }
                } else if (op.charAt(0) == 'M') {
                    get_available_position(board, current_player, position);
                    if (position.size() == 0 ||
                        !position.contains("(" + (op.charAt(1) - '0') +
                                           "," + (op.charAt(2) - '0') +
                                           ")")) {
                        current_player = !current_player;
                    }
                    make_a_move(board,
                                current_player,
                                op.charAt(1) - '0' - 1,
                                op.charAt(2) - '0' - 1,
                                cnt);
                    System.out.printf("Black - %2d White - %2d\n",
                                      cnt[0], cnt[1]);
                    current_player = !current_player;
                }
            }

            for (char[] c : board) {
                for (char cc : c) {
                    System.out.print(cc);
                }
                System.out.println();
            }
            if (T != 0) {
                System.out.println();
            }
        }
    }
}
