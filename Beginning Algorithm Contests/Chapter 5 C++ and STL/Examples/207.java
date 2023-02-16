import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    static int find_tie_rank_count(ArrayList<Player> players) {
        int res = players.size();
        for (int i = 69; i < res; i += 1) {
            if (i == res - 1 ||
                players.get(i).tot36 != players.get(i + 1).tot36) {
                res = i + 1;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int cases = stdin.nextInt();
        BigDecimal purse = null;
        int n = 0;
        String delta = null;
        String[] alpha = null;
        Player p = null;
        BigDecimal[] percents = new BigDecimal[70];
        ArrayList<Player> players = new ArrayList<Player>();
        while ((cases -= 1) >= 0) {
            players.clear();

            /* Input part 1, money */
            purse = new BigDecimal(stdin.next());
            //purse_left = purse.doubleValue();
            for (int i = 0; i < 70; i += 1) {
                percents[i] = new BigDecimal(stdin.next());
            }

            /* Players */
            n = stdin.nextInt();
            stdin.skip("\r|\n|\r\n");
            for (int i = 0; i < n; i += 1) {
                delta = stdin.nextLine();
                /* Name */
                p = new Player(delta.substring(0, 20).trim());
                if (p.name.endsWith("*")) {
                    p.is_amateur = true;
                }
                /* Scores */
                alpha = delta.substring(20).trim().split("\\s+");
                for (int j = 0; j < alpha.length; j += 1) {
                    if (alpha[j].equals("DQ")) {
                        p.rounds = j;
                        p.is_dq = true;
                        if (j < 2) {
                            p.tot36 = -1;
                        }
                        break;
                    } else {
                        p.round[j] = Integer.parseInt(alpha[j]);
                        p.tot72 += p.round[j];
                        if (j < 2) {
                            p.tot36 += p.round[j];
                        }
                    }
                }
                players.add(p);
            }
            /* Sort: make the cut */
            Collections.sort(players, (p1, p2) -> {
                    if (p1.tot36 < 0 && p2.tot36 < 0) {
                        return 0;
                    }
                    if (p1.tot36 < 0) {
                        return 1;
                    }
                    if (p2.tot36 < 0) {
                        return -1;
                    }
                    return Integer.compare(p1.tot36, p2.tot36);
                });
            n = find_tie_rank_count(players);
            players = new ArrayList<Player>(players.subList(0, n));
            /* Sort: ranklist */
            Collections.sort(players, (p1, p2) -> {
                    if (p1.is_dq && p2.is_dq) {
                        if (p1.rounds != p2.rounds) {
                            return Integer.compare(p2.rounds, p1.rounds);
                        }
                        if (p1.tot72 != p2.tot72) {
                            return Integer.compare(p1.tot72, p2.tot72);
                        }
                        return p1.name.compareTo(p2.name);
                    }
                    if (p1.is_dq) {
                        return 1;
                    }
                    if (p2.is_dq) {
                        return -1;
                    }
                    if (p1.tot72 != p2.tot72) {
                        return Integer.compare(p1.tot72, p2.tot72);
                    }
                    return p1.name.compareTo(p2.name);
                });
            /* Print result */
            System.out.println("Player Name          Place     RD1  RD2  RD3" +
                               "  RD4  TOTAL     Money Won\n" +
                               "--------------------------------------------" +
                               "---------------------------");
            int i = 0;
            int pos = 0;
            while (i < n) {
                if (players.get(i).is_dq) {
                    System.out.printf("%-30s ", players.get(i).name);
                    for (int j = 0; j < players.get(i).rounds; j += 1) {
                        System.out.printf("%-5d", players.get(i).round[j]);
                    }
                    for (int j = 0; j < 4 - players.get(i).rounds; j += 1) {
                        System.out.print("     ");
                    }
                    System.out.println("DQ");
                    i += 1;
                    continue;
                }
                int j = i;
                int tie_cnt = 0;
                boolean has_money = false;
                BigDecimal tot = BigDecimal.ZERO;
                while (j < n && players.get(i).tot72 == players.get(j).tot72) {
                    if (!players.get(j).is_amateur) {
                        tie_cnt += 1;
                        if (pos < 70) {
                            has_money = true;
                            tot = tot.add(percents[pos]);
                            pos += 1;
                        }
                    }
                    j += 1;
                 }

                int rank = i + 1;
                double award = 0;
                if (tie_cnt == 0) {
                    award = 0;
                } else {
                    award = purse.multiply(tot).divide(new BigDecimal(tie_cnt),
                                                       10,
                                                       RoundingMode.HALF_UP)
                        .doubleValue();
                }

                while (i < j) {
                    System.out.printf("%-20s ", players.get(i).name);
                    String r = rank +
                        (tie_cnt > 1 && has_money && !players.get(i).is_amateur
                         ? "T" : " ");
                    System.out.printf("%-10s", r);
                    for (int k = 0; k < 4; k += 1) {
                        System.out.printf("%-5d", players.get(i).round[k]);
                    }

                    if (!players.get(i).is_amateur && has_money) {
                        System.out.printf("%-10d", players.get(i).tot72);
                        System.out.printf("$%9.2f\n", award / 100);
                    } else {
                        System.out.println(players.get(i).tot72);
                    }
                    i += 1;
                }
            }
            if (cases > 0) {
                System.out.println();
            }
        }
    }
}

class Player {
    String name;
    int[] round;
    boolean is_amateur;
    boolean is_dq;

    /* Only meaningful when player is not dq */
    int tot72;
    int tot36;
    int rounds;

    Player(String name) {
        this.name = name;
        round = new int[4];
        is_amateur = false;
        is_dq = false;
        tot36 = 0;
        tot72 = 0;
    }
}
