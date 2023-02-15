import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    static int find_tie_rank_count(ArrayList<Player> players, int rank) {
        int res = players.size();
        for (int i = 69; i < res; i += 1) {
            if (i == res - 1 ||
                players.get(i).round[0] +
                players.get(i).round[1] !=
                players.get(i + 1).round[0] +
                players.get(i + 1).round[1]) {
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
        double purse_left = 0;
        //final int BIAS = 10000;
        int n = 0;
        int m = 0;
        int rd = 0;
        String delta = null;
        String[] alpha = null;
        Player p = null;
        //double[] percents = new double[70];
        BigDecimal[] percents = new BigDecimal[70];
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Player> dq_list = new ArrayList<Player>();
        ArrayList<Player> am_list = new ArrayList<Player>();
        LinkedList<LinkedList<Player>> output =
            new LinkedList<LinkedList<Player>>();
        LinkedList<LinkedList<Player>> money =
            new LinkedList<LinkedList<Player>>();
        while ((cases -= 1) >= 0) {
            players.clear();
            dq_list.clear();
            output.clear();
            am_list.clear();
            money.clear();

            purse = new BigDecimal(stdin.next());
            purse_left = purse.doubleValue();
            for (int i = 0; i < 70; i += 1) {
                percents[i] = new BigDecimal(stdin.next());
            }
            n = stdin.nextInt();
            stdin.skip("\r|\n|\r\n");
            for (int i = 0; i < n; i += 1) {
                delta = stdin.nextLine();
                p = new Player(delta.substring(0, 20).trim());
                if (p.name.endsWith("*")) {
                    p.is_amateur = true;
                }
                alpha = delta.substring(20).trim().split("\\s+");
                /*for (var j = 0; j < 4; j += 1) {
                    System.out.println(alpha[j]);
                    }*/
                for (int j = 0; j < alpha.length; j += 1) {
                    //if (alpha[j].length() < 1) {
                    //    continue;
                    //}
                    if (alpha[j].equals("DQ")) {
                        for (int k = j; k < 4; k += 1) {
                            p.round[k] = 100;
                        }
                        p.is_dq = true;
                        break;
                    } else {
                        p.round[j] = Integer.parseInt(alpha[j]);
                    }
                }
                if (!(p.round[0] >= 100 || p.round[1] >= 100)) {
                    players.add(p);
                }
            }
            for (Player i : players) {
                i.tot36 = i.round[0] + i.round[1];
            }
            Collections.sort(players, (p1, p2) -> {
                    return Integer.compare(p1.tot36, p2.tot36);
                });
            m = find_tie_rank_count(players, 70);

            /* Remove not pass */
            for (int i = 0, len = players.size() - m; i < len; i += 1) {
                players.remove(players.size() - 1);
            }
            dq_list = new ArrayList<Player>(players
                                            .stream()
                                            .filter(p1 -> p1.is_dq)
                                            .collect(Collectors.toList()));
            players.removeAll(dq_list);
            for (int i = 0; i < players.size(); i += 1) {
                players.get(i).tot =
                    players.get(i).round[0] +
                    players.get(i).round[1] +
                    players.get(i).round[2] +
                    players.get(i).round[3];
            }
            Collections.sort(players, (p1, p2) -> {
                    if (p1.tot == p2.tot) {
                        return p1.name.compareTo(p2.name);
                    }
                    return Integer.compare(p1.tot, p2.tot);
                });

            output.add(new LinkedList<Player>());
            output.getLast().add(players.get(0));
            for (int i = 1; i < players.size(); i += 1) {
                if (players.get(i).tot != players.get(i - 1).tot) {
                    /* Create new list */
                    output.add(new LinkedList<Player>());
                }
                output.getLast().add(players.get(i));
            }
            // get rank
            int rank = 1;
            for (LinkedList<Player> j : output) {
                int amateur_count = 0;
                for (Player i : j) {
                    if (i.is_amateur) {
                        amateur_count += 1;
                    }
                }
                for (Player i : j) {
                    i.rank = rank + (j.size() - amateur_count> 1 && !i.is_amateur ? "T" : "");
                    /*System.out.printf("%s %d%s %d %d %d %d\n",
                                      i.name,
                                      rank,
                                      j.size() - amateur_count> 1 && !i.is_amateur ? "T" : "",
                                      i.round[0],
                                      i.round[1],
                                      i.round[2],
                                      i.round[3]);*/
                }
                rank += j.size();
            }
            am_list = new ArrayList<Player>(players
                                            .stream()
                                            .filter(p1 -> p1.is_amateur)
                                            .collect(Collectors.toList()));
            players.removeAll(am_list);
            money.add(new LinkedList<Player>());
            money.getLast().add(players.get(0));
            for (int i = 1; i < players.size(); i += 1) {
                if (players.get(i).tot != players.get(i - 1).tot) {
                    /* Create new list */
                    money.add(new LinkedList<Player>());
                }
                money.getLast().add(players.get(i));
            }
            rank = 0;
            for (LinkedList<Player> j : money) {
                if (purse_left < 0) {
                    for (Player i : j) {
                        i.rank = i.rank.replace("T", "");
                        i.is_no_money = true;
                    }
                }
                BigDecimal ave_rate = BigDecimal.ZERO;
                for (Player i : j) {
                    if (rank >= 70) {
                        break;
                    }
                    //ave_rate += percents[rank];
                    ave_rate = ave_rate.add(percents[rank]);

                    rank += 1;
                }
                //double ave =  ave_rate / j.size();
                BigDecimal beta = new BigDecimal("100").multiply(new BigDecimal(j.size()));
                BigDecimal award = (ave_rate.multiply(purse))
                    .divide(beta, 10, RoundingMode.HALF_UP);
                for (Player i : j) {
                    purse_left -= award.doubleValue();
                    i.award = award.doubleValue();
                    /*if (rank > 70) {
                        i.award = -1;
                        }*/
                }


            }
            players.addAll(am_list);
            Collections.sort(players, (p1, p2) -> {
                    if (p1.tot == p2.tot) {
                        return p1.name.compareTo(p2.name);
                    }
                    return Integer.compare(p1.tot, p2.tot);
                });
            System.out.println("Player Name          Place     RD1  RD2  RD3" +
                               "  RD4  TOTAL     Money Won\n" +
                               "--------------------------------------------" +
                               "---------------------------");

            for (Player i : players) {

                System.out.printf("%-20s %-9s %-4d %-4d %-4d %-4d ",
                                  i.name,
                                  i.rank,
                                  i.round[0],
                                  i.round[1],
                                  i.round[2],
                                  i.round[3]);
                if (!i.is_amateur && !i.is_no_money) {
                    System.out.printf("%-9d ", i.tot);
                } else {
                    System.out.printf("%d", i.tot);
                }
                if (i.is_amateur || i.is_no_money) {
                    System.out.println();
                } else {
                    /*if (Integer.parseInt(i.rank.replace("T", "")) > 70) {
                        System.out.println();
                        } else {*/
                        System.out.printf("$%9.2f\n", i.award);
                        //}
                }
            }
            Collections.sort(dq_list, (p1, p2) -> {
                    int rd_cnt1 = 0;
                    int rd_cnt2 = 0;
                    int tot1 = 0;
                    int tot2 = 0;
                    for (int i = 0; i < 4; i += 1) {
                        if (p1.round[i] >= 100) {
                            rd_cnt1 = i;
                            break;
                        }
                        tot1 += p1.round[i];
                    }
                    for (int i = 0; i < 4; i += 1) {
                        if (p2.round[i] >= 100) {
                            rd_cnt2 = i;
                            break;
                        }
                        tot2 +=	p2.round[i];
                    }
                    if (rd_cnt1 != rd_cnt2) {
                        return Integer.compare(rd_cnt2, rd_cnt1);
                    }
                    if (tot1 != tot2) {
                        return Integer.compare(tot1, tot2);
                    }
                    return p1.name.compareTo(p2.name);
                });
            for (Player i : dq_list) {
                System.out.printf("%-20s %-9s %-4s %-4s %-4s %-4s %s\n",
                                  i.name,
                                  "",
                                  i.round[0] >= 100 ? "" : i.round[0],
                                  i.round[1] >= 100 ? "" : i.round[1],
                                  i.round[2] >= 100 ? "" : i.round[2],
                                  i.round[3] >= 100 ? "" : i.round[3],
                                  "DQ");
            }
            System.out.println();
        }
    }
}

class Player {
    String name;
    int[] round;
    boolean is_amateur;
    boolean is_dq;

    /* Only meaningful when player is not dq */
    int tot;
    int tot36;
    String rank;
    double award;
    boolean is_no_money;

    Player(String name) {
        this.name = name;
        round = new int[4];
        is_amateur = false;
        is_dq = false;
        is_no_money = false;
    }
}
