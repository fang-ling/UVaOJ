import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String delta = null;
        StringBuilder msg = null;
        int n = 0;
        boolean contains = false;
        boolean is_send_data = false;
        boolean is_valid_user = false;
        User sender = null;
        User u = null;
        ArrayList<MTA> mtas = new ArrayList<MTA>();
        LinkedList<LinkedList<User>> rcpts =
            new LinkedList<LinkedList<User>>();
        /* Read MTAs */
        while (!(delta = stdin.next()).equals("*")) {
            if (delta.equals("MTA")) {
                mtas.add(new MTA(stdin.next()));
                n = stdin.nextInt();
                for (int i = 0; i < n; i += 1) {
                    mtas.get(mtas.size() - 1).names.add(stdin.next());
                }
            }
        }
        /* Handle addrs and messages */
        while (!(delta = stdin.next()).equals("*")) {
            rcpts.clear();

            /* Read RCPTs */
            sender = new User(delta);
            while (!(delta = stdin.next()).equals("*")) {
                u = new User(delta);
                contains = false;
                for (LinkedList<User> j : rcpts) {
                    if (j.contains(u)) { /* Duplicated addr */
                        contains = true;
                        continue;
                    }
                    for (User i : j) {
                        if (i.MTA.equals(u.MTA)) {
                            contains = true;
                            j.addLast(u);
                            break;
                        }
                    }
                }
                if (!contains) {
                    rcpts.addLast(new LinkedList<User>());
                    rcpts.getLast().addLast(u);
                }
            }
            msg = new StringBuilder();
            stdin.skip("\r|\n|\r\n");
            while (!(delta = stdin.nextLine()).equals("*")) {
                msg.append("     ");
                msg.append(delta);
                msg.append("\n");
            }

            for (LinkedList<User> i : rcpts) {
                System.out.printf("Connection between %s and %s\n",
                                  sender.MTA, i.getFirst().MTA);
                System.out.printf("     HELO %s\n", sender.MTA);
                System.out.println("     250");
                System.out.printf("     MAIL FROM:<%s>\n", sender.to_string());
                System.out.println("     250");
                is_send_data = false;
                for (User j : i) {
                    is_valid_user = false;
                    for (MTA m : mtas) {
                        if (m.location.equals(j.MTA) &&
                            m.names.contains(j.name)) {
                            is_valid_user = true;
                            break;
                        }
                    }
                    System.out.printf("     RCPT TO:<%s>\n", j.to_string());
                    if (is_valid_user) {
                        is_send_data = true;
                        System.out.println("     250");
                    } else {
                        System.out.println("     550");
                    }
                }
                if (is_send_data) {
                    System.out.println("     DATA");
                    System.out.println("     354");
                    System.out.print(msg.toString());
                    System.out.println("     .");
                    System.out.println("     250");
                }
                System.out.println("     QUIT");
                System.out.println("     221");
            }
        }
    }
}

class MTA {
    public String location;
    public ArrayList<String> names;

    public MTA(String location) {
        this.location = location;
        names = new ArrayList<String>();
    }
}

class User {
    public String name;
    public String MTA;

    /* username@MTA */
    public User(String addr) {
        String[] buf = addr.split("@");
        name = buf[0];
        MTA = buf[1];
    }

    public String to_string() {
        return name + "@" + MTA;
    }

    @Override
    public boolean equals(Object another) {
        if (another == this) {
            return true;
        }
        if (another.getClass() != this.getClass()) {
            return false;
        }
        final User delta = (User) another;
        return delta.name.equals(name) && delta.MTA.equals(MTA);
    }
}
