import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static void init_board(char[][] board) {
        for (int i = 0; i < 10; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                board[i][j] = ' ';
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        /* Black general: 'B'
         * Forbidden region: 'X'
         */
        char[][] board = new char[10][9];
        char[][] ll = null;
        ArrayList<char[][]> layer = new ArrayList<char[][]>();
        int n = 0;
        int bg_x = 0;
        int bg_y = 0;
        char p = '0';
        int bet_x = 0;
        int bet_y = 0;
        int bet2_x = 0;
        int bet2_y = 0;
        boolean is_piece_between_gens = false;
        boolean is_behind_cannon = false;
        boolean is_behind_behind_cannon = false;
        boolean is_win = false;
        while (stdin.hasNext()) {
            n = stdin.nextInt();
            bg_x = stdin.nextInt() - 1;
            bg_y = stdin.nextInt() - 1;
            if (n == 0 && bg_x == -1 && bg_y == -1) {
                break;
            }

            /* Init board & layer */
            layer.clear();
            init_board(board);

            //board[bg_x][bg_y] = 'B';
            for (int i = 0; i < n; i += 1) {
                p = stdin.next().charAt(0);
                board[stdin.nextInt() - 1][stdin.nextInt() - 1] = p;
            }
            /* Mark forbidden region */
            is_win = true;
            for (int i = 0; i < 10; i += 1) {
                for (int j = 0; j < 9; j += 1) {
                    if (board[i][j] == 'G') {
                        is_piece_between_gens = false;
                        /* Check if there is a piece between two generals */
                        for (int k = i; k > bg_x; k -= 1) {
                            if (board[k][j] != ' ' &&
                                board[k][j] != 'X' &&
                                board[k][j] != 'B' &&
                                board[k][j] != 'G') {
                                is_piece_between_gens = true;
                                ll = new char[10][9];
                                init_board(ll);
                                for (int kk = i; kk >= k; kk -= 1) {
                                    ll[kk][j] = 'X';
                                }
                                layer.add(ll);
                                break;
                            }
                        }
                        if (!is_piece_between_gens) {
                            /* Special case: if two generals can see each other
                             */
                            if (bg_y == j) {
                                is_win = false;
                                break;
                            }
                            ll = new char[10][9];
                            init_board(ll);
                            for (int k = i; k >= 0; k -= 1) {
                                //if (board[k][j] == ' ') {
                                    //board[k][j] = 'X';
                                    ll[k][j] = 'X';
                                    //}
                            }
                            layer.add(ll);
                        }
                    } else if (board[i][j] == 'R') {
                        ll = new char[10][9];
                        init_board(ll);
                        /* From chariot down */
                        for (int k = i + 1; k < 10; k += 1) {
                            if (board[k][j] == ' ') {
                                //board[k][j] = 'X';
                                ll[k][j] = 'X';
                            } else if (board[k][j] == 'G' ||
                                       board[k][j] == 'R' ||
                                       board[k][j] == 'C' ||
                                       board[k][j] == 'H') {
                                ll[k][j] = 'X';
                                break;
                            }
                        }
                        /* From chariot up */
                        for (int k = i - 1; k >= 0; k -= 1) {
                            if (board[k][j] == ' ') {
                                //board[k][j] = 'X';
                                ll[k][j] = 'X';
                            } else if (board[k][j] == 'G' ||
                                       board[k][j] == 'R' ||
                                       board[k][j] == 'C' ||
                                       board[k][j] == 'H') {
                                ll[k][j] = 'X';
                                break;
                            }
                        }
                        /* From chariot left */
                        for (int k = j - 1; k >= 0; k -= 1) {
                            if (board[i][k] == ' ') {
                                //board[i][k] = 'X';
                                ll[i][k] = 'X';
                            } else if (board[i][k] == 'G' ||
                                       board[i][k] == 'R' ||
                                       board[i][k] == 'C' ||
                                       board[i][k] == 'H') {
                                ll[i][k] = 'X';
                                break;
                            }
                        }
                        /* From chariot right */
                        for (int k = j + 1; k < 9; k += 1) {
                            if (board[i][k] == ' ') {
                                //board[i][k] = 'X';
                                ll[i][k] = 'X';
                            } else if (board[i][k] == 'G' ||
                                       board[i][k] == 'R' ||
                                       board[i][k] == 'C' ||
                                       board[i][k] == 'H') {
                                ll[i][k] = 'X';
                                break;
                            }
                        }
                        layer.add(ll);
                    } else if (board[i][j] == 'H') {
                        ll = new char[10][9];
                        init_board(ll);
                        if (j + 2 < 9 &&
                            (board[i][j + 1] == ' ' ||
                             board[i][j + 1] == 'X')) {
                            if (i - 1 >= 0/* && board[i - 1][j + 2] == ' '*/) {
                                //board[i - 1][j + 2] = 'X';
                                ll[i - 1][j + 2] = 'X';
                            }
                            if (i + 1 < 10/* && board[i + 1][j + 2] == ' '*/) {
                                //board[i + 1][j + 2] = 'X';
                                ll[i + 1][j + 2] = 'X';
                            }
                        }
                        if (i - 2 >= 0 &&
                            (board[i - 1][j] == ' ' ||
                             board[i - 1][j] == 'X')) {
                            if (j - 1 >= 0/* && board[i - 2][j - 1] == ' '*/) {
                                //board[i - 2][j - 1] = 'X';
                                ll[i - 2][j - 1] = 'X';
                            }
                            if (j + 1 < 9/* && board[i - 2][j + 1] == ' '*/) {
                                //board[i - 2][j + 1] = 'X';
                                ll[i - 2][j + 1] = 'X';
                            }
                        }
                        if (j - 2 >= 0 &&
                            (board[i][j - 1] == ' ' ||
                             board[i][j - 1] == 'X')) {
                            if (i - 1 >= 0/* && board[i - 1][j - 2] == ' '*/) {
                                //board[i - 1][j - 2] = 'X';
                                ll[i - 1][j - 2] = 'X';
                            }
                            if (i + 1 < 10/* && board[i + 1][j - 2] == ' '*/) {
                                //board[i + 1][j - 2] = 'X';
                                ll[i + 1][j - 2] = 'X';
                            }
                        }
                        if (i + 2 < 10 &&
                            (board[i + 1][j] == ' ' ||
                             board[i + 1][j] == 'X')) {
                            if (j - 1 >= 0/* && board[i + 2][j - 1] == ' '*/) {
                                //board[i + 2][j - 1] = 'X';
                                ll[i + 2][j - 1] = 'X';
                            }
                            if (j + 1 < 9/* && board[i + 2][j + 1] == ' '*/) {
                                //board[i + 2][j + 1] = 'X';
                                ll[i + 2][j + 1] = 'X';
                            }
                        }
                        layer.add(ll);
                    } else if (board[i][j] == 'C') {
                        ll = new char[10][9];
                        init_board(ll);
                        /* Down */
                        is_behind_cannon = false;
                        for (int k = i + 1; k < 10; k += 1) {
                            if (board[k][j] != ' ' && board[k][j] != 'X') {
                                bet_x = k;
                                bet_y = j;
                                is_behind_cannon = true;
                                break;
                            }
                        }
                        is_behind_behind_cannon = false;
                        for (int k = bet_x + 1; k < 10; k += 1) {
                            if (board[k][j] != ' ' && board[k][j] != 'X') {
                                bet2_x = k;
                                bet2_y = j;
                                is_behind_behind_cannon = true;
                                break;
                            }
                        }
                        if (is_behind_cannon && is_behind_behind_cannon) {
                            for (int k = bet_x + 1; k < bet2_x; k += 1) {
                                if (board[k][j] == ' ') {
                                    //board[k][j] = 'X';
                                    ll[k][j] = 'X';
                                }
                            }
                        }
                        if (is_behind_cannon && !is_behind_behind_cannon) {
                            for (int k = bet_x + 1; k < 10; k += 1) {
                                if (board[k][j] == ' ') {
                                    //board[k][j] = 'X';
                                    ll[k][j] = 'X';
                                }
                            }
                        }
                        /* Up */
                        is_behind_cannon = false;
                        for (int k = i - 1; k >= 0; k -= 1) {
                            if (board[k][j] != ' ' && board[k][j] != 'X') {
                                bet_x = k;
                                bet_y = j;
                                is_behind_cannon = true;
                                break;
                            }
                        }
                        is_behind_behind_cannon = false;
                        for (int k = bet_x - 1; k >= 0; k -= 1) {
                            if (board[k][j] != ' ' && board[k][j] != 'X') {
                                bet2_x = k;
                                bet2_y = j;
                                is_behind_behind_cannon = true;
                                break;
                            }
                        }
                        if (is_behind_cannon && is_behind_behind_cannon) {
                            for (int k = bet_x - 1; k > bet2_x; k -= 1) {
                                if (board[k][j] == ' ') {
                                    //board[k][j] = 'X';
                                    ll[k][j] = 'X';
                                }
                            }
                        }
                        if (is_behind_cannon && !is_behind_behind_cannon) {
                            for (int k = bet_x - 1; k >= 0; k -= 1) {
                                if (board[k][j] == ' ') {
                                    //board[k][j] = 'X';
                                    ll[k][j] = 'X';
                                }
                            }
                        }
                        /* Right */
                        is_behind_cannon = false;
                        for (int k = j + 1; k < 9; k += 1) {
                            if (board[i][k] != ' ' && board[i][k] != 'X') {
                                bet_x = i;
                                bet_y = k;
                                is_behind_cannon = true;
                                break;
                            }
                        }
                        is_behind_behind_cannon = false;
                        for (int k = bet_y + 1; k < 9; k += 1) {
                            if (board[i][k] != ' ' && board[i][k] != 'X') {
                                bet2_x = i;
                                bet2_y = k;
                                is_behind_behind_cannon = true;
                                break;
                            }
                        }
                        if (is_behind_cannon && is_behind_behind_cannon) {
                            for (int k = bet_y + 1; k < bet2_y; k += 1) {
                                if (board[i][k] == ' ') {
                                    //board[i][k] = 'X';
                                    ll[i][k] = 'X';
                                }
                            }
                        }
                        if (is_behind_cannon && !is_behind_behind_cannon) {
                            for (int k = bet_y + 1; k < 9; k += 1) {
                                if (board[i][k] == ' ') {
                                    //board[i][k] = 'X';
                                    ll[i][k] = 'X';
                                }
                            }
                        }
                        /* Left */
                        is_behind_cannon = false;
                        for (int k = j - 1; k >= 0; k -= 1) {
                            if (board[i][k] != ' ' && board[i][k] != 'X') {
                                bet_x = i;
                                bet_y = k;
                                is_behind_cannon = true;
                                break;
                            }
                        }
                        is_behind_behind_cannon = false;
                        for (int k = bet_y - 1; k >= 0; k -= 1) {
                            if (board[i][k] != ' ' && board[i][k] != 'X') {
                                bet2_x = i;
                                bet2_y = k;
                                is_behind_behind_cannon = true;
                                break;
                            }
                        }
                        if (is_behind_cannon && is_behind_behind_cannon) {
                            for (int k = bet_y - 1; k > bet2_y; k -= 1) {
                                if (board[i][k] == ' ') {
                                    //board[i][k] = 'X';
                                    ll[i][k] = 'X';
                                }
                            }
                        }
                        if (is_behind_cannon && !is_behind_behind_cannon) {
                            for (int k = bet_y - 1; k >= 0; k -= 1) {
                                if (board[i][k] == ' ') {
                                    //board[i][k] = 'X';
                                    ll[i][k] = 'X';
                                }
                            }
                        }
                        layer.add(ll);
                    }
                }
            }
            if (!is_win) {
                System.out.println("NO");
            } else {
                is_win = false;
                if (bg_x == 1 - 1 && bg_y == 4 - 1) {
                    for (char[][] l : layer) {
                        if (l[2 - 1][4 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[1 - 1][5 - 1] == 'X') {
                                    is_win = true;
                                }
                            }
                        }
                    }
                } else if (bg_x == 2 - 1 && bg_y == 4 - 1) {
                    for (char[][] l : layer) {
                        if (l[3 - 1][4 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[2 - 1][5 - 1] == 'X') {
                                    for (char[][] k : layer) {
                                        if (k[1 - 1][4 - 1] == 'X') {
                                            is_win = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (bg_x == 3 - 1 && bg_y == 4 - 1) {
                    for	(char[][] l : layer) {
                        if (l[2 - 1][4 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[3 - 1][5 - 1] == 'X') {
                                    is_win = true;
                                }
                            }
                        }
                    }
                } else if (bg_x == 1 - 1 && bg_y == 5 - 1) {
                    for (char[][] l : layer) {
                        if (l[1 - 1][4 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[1 - 1][6 - 1] == 'X') {
                                    for (char[][] k : layer) {
                                        if (k[2 - 1][5 - 1] == 'X') {
                                            is_win = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (bg_x == 2 - 1 && bg_y == 5 - 1) {
                    for (char[][] l : layer) {
                        if (l[2 - 1][4 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[2 - 1][6 - 1] == 'X') {
                                    for (char[][] k : layer) {
                                        if (k[3 - 1][5 - 1] == 'X') {
                                            for (char[][] m : layer) {
                                                if (m[1 - 1][5 - 1] == 'X') {
                                                    is_win = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (bg_x == 3 - 1 && bg_y == 5 - 1) {
                    for (char[][] l : layer) {
                        if (l[3 - 1][4 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[3 - 1][6 - 1] == 'X') {
                                    for (char[][] k : layer) {
                                        if (k[2 - 1][5 - 1] == 'X') {
                                            is_win = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (bg_x == 1 - 1 && bg_y == 6 - 1) {
                    for (char[][] l : layer) {
                        if (l[1 - 1][5 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[2 - 1][6 - 1] == 'X') {
                                    is_win = true;
                                }
                            }
                        }
                    }
                } else if (bg_x == 2 - 1 && bg_y == 6 - 1) {
                    for (char[][] l : layer) {
                        if (l[1 - 1][6 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[2 - 1][5 - 1] == 'X') {
                                    for (char[][] k : layer) {
                                        if (k[3 - 1][6 - 1] == 'X') {
                                            is_win = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (bg_x == 3 - 1 && bg_y == 6 - 1) {
                    for (char[][] l : layer) {
                        if (l[2 - 1][6 - 1] == 'X') {
                            for (char[][] j : layer) {
                                if (j[3 - 1][5 - 1] == 'X') {
                                    is_win = true;
                                }
                            }
                        }
                    }
                }
                if (is_win) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
            for (char[][] lll : layer) {
                for (int i = 0; i < 10; i += 1) {
                    for (int j = 0; j < 9; j += 1) {
                        //System.out.print(lll[i][j]);
                    }
                    //System.out.println();
                }
                //System.out.println("---------");
            }
        }
    }
}
