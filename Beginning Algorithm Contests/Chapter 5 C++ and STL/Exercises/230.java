import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        Scanner stdin =
            new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        TreeSet<Book> shelf = new TreeSet<Book>();
        TreeSet<Book> desk = new TreeSet<Book>();
        HashMap<String, String> authors = new HashMap<String, String>();
        String delta = null;
        String op = null;
        String title = null;
        String[] book_data = null;
        Book book = null;
        while (!(delta = stdin.nextLine()).equals("END")) {
            book_data = delta.split("by");
            shelf.add(new Book(book_data[0].trim(), book_data[1].trim()));
            authors.put(book_data[0].trim(), book_data[1].trim());
        }
        while (!(delta = stdin.nextLine()).equals("END")) {
            op = delta.substring(0, 6);
            if (op.equals("SHELVE")) {
                while (desk.size() > 0) {
                    book = shelf.lower(desk.first());
                    if (book == null) {
                        System.out.printf("Put %s first\n",
                                          desk.first().title);
                    } else {
                        System.out.printf("Put %s after %s\n",
                                          desk.first().title, book.title);
                    }
                    shelf.add(desk.first());
                    desk.remove(desk.first());
                }
                System.out.println("END");
            } else {
                title = delta.substring(7, delta.length());
                if (op.equals("BORROW")) {
                    /* O(n) search */
                    for (Book b : shelf) {
                        if (b.title.equals(title)) {
                            book = b;
                            break;
                        }
                    }
                    shelf.remove(book);
                } else {
                    desk.add(new Book(title, authors.get(title)));
                }
            }

        }
    }
}

class Book implements Comparable<Book> {
    String author;
    String title;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public int compareTo(Book another) {
        if (author.equals(another.author)) {
            return title.compareTo(another.title);
        }
        return author.compareTo(another.author);
    }
}
