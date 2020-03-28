
/**
 * Runs the book-tracker
 * 
 * @author brendanbab8
 * @version 1.1
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Main {
  /** The library object where all books are stored. */
  private static Library library = new Library();

  /**
   * checkPages checks for a valid page number. Pages must be positive (>=0).
   * 
   * @param pages
   * @return the number of pages.
   */
  private static int checkPages(int pages, Scanner input) {
    if (pages > -1) {
      return pages;
    } else {
      System.out.println("Page number not valid. Please enter a positive number.");
      int newPages = input.nextInt();
      return checkPages(newPages, input);
    }
  }

  /**
   * checkRating checks for a valid rating. Ratings must be in the range 0-10
   * (inclusive).
   * 
   * @param rating
   * @return the rating of the book.
   */
  private static int checkRating(int rating, Scanner input) {
    if (rating > -1 && rating < 11) {
      return rating;
    } else {
      System.out.println("Rating not valid. Please enter a number between 0 and 10.");
      int newRate = input.nextInt();
      return checkPages(newRate, input);
    }
  }

  /**
   * addBook is the process for adding a book.
   * 
   * @param input The input channel for user input.
   */
  private static void addBook(Scanner input) {
    String title, author, publisher, series, genre;
    int pages, rating;

    System.out.println("Enter the book's title.");
    title = input.nextLine();
    System.out.println("Enter the book's author.");
    author = input.nextLine();
    System.out.println("Enter the book's publisher.");
    publisher = input.nextLine();
    System.out.println("Enter the book's series (if applicable).  If the book is a standalone, put 'None'");
    series = input.nextLine();
    System.out.println("Enter the book's genre. This will be the shelf the book will reside in");
    genre = input.nextLine();
    System.out.println("Enter the number of pages. (Must be a whole number.)");
    pages = input.nextInt();
    pages = checkPages(pages, input);
    System.out.println("Enter the rating for the book, or zero if the book is unread. (Must be a whole number.)");
    rating = input.nextInt();
    rating = checkRating(rating, input);

    try {
      Shelf s = library.getShelf(genre);
      s.addBook(new Book(title, author, pages, publisher, series, genre, rating));
    } catch (Exception e) {
      Shelf s = new Shelf(genre);
      s.addBook(new Book(title, author, pages, publisher, series, genre, rating));
      library.addShelf(s);
    }
  }

  /**
   * printShelf is the printing of a given shelf.
   * 
   * @param input the input channel for user input
   * @return The shelf printed
   */
  private static Shelf printShelf(Scanner input) {
    System.out.println("Here is a list of shelves you have in your library.\n");
    System.out.println(library.toString() + "\n");
    System.out.println("Enter the name of the shelf you want to view: ");
    String choice = input.nextLine();

    try {
      Shelf s = library.getShelf(choice);
      System.out.println(s.toString() + "\n");
      return s;
    } catch (Exception e) {
      System.out.println("Shelf not found. Please try again.");
      printShelf(input);
    }
    return null;
  }

  /**
   * fixBook handles user corrections for an incorrect book.
   * 
   * @param input The input channel for user input
   * @param shelf The shelf in which the book is contained.
   * @return The requested book.
   */
  private static Book fixBook(Scanner input, Shelf shelf) {
    System.out.println("That book wasn't found.  Please reenter the title.");
    String choice = input.nextLine();
    Book book = shelf.findBook(choice);
    if (book == null) {
      book = fixBook(input, shelf);
    }
    return book;
  }

  /**
   * markRead is the marking of a book to be read.
   * 
   * @param input
   */
  private static void markRead(Scanner input) {
    System.out.println("The shelf of your choice will be displayed for your convenience.");
    Shelf s = printShelf(input);
    System.out.println("\nWhich book would you like to mark read/ change the rating? Please enter the full title: ");
    String choice = input.nextLine();
    Book book = s.findBook(choice);
    if (book == null) {
      book = fixBook(input, s);
    }
    System.out.println("Please enter the new rating.");
    int rating = input.nextInt();
    rating = checkRating(rating, input);
    book.setRating(rating);
  }

  /**
   * options allows menu items to be accessed by multiple methods.
   * 
   * @param choice The user selected option.
   * @param input  The input channel for user input.
   * @throws IOException
   */
  private static void options(String choice, Scanner input) throws IOException {
    if (choice.equalsIgnoreCase("a")) {
      addBook(input);
      System.out.println("Book added successfully!");
      input.nextLine();
      menu(input);
    } else if (choice.equalsIgnoreCase("x")) {
      input.close();
      library.writeToFile();
      System.out.print("Goodbye!\n");
    } else if (choice.equalsIgnoreCase("s")) {
      System.out.println(library.toString() + "\n");
      menu(input);
    } else if (choice.equalsIgnoreCase("g")) {
      printShelf(input);
      menu(input);
    } else if (choice.equalsIgnoreCase("r")) {
      markRead(input);
      System.out.println("Rating successfully changed!");
      input.nextLine();
      menu(input);
    } else if (choice.equalsIgnoreCase("t")) {
      System.out.printf("Total: %-10d\n", library.totalBooks());
      menu(input);
    } else if (choice.equalsIgnoreCase("f")) {
      System.out.printf("Total read: %10d/%-10d\n", library.booksRead(), library.totalBooks());
      menu(input);
    } else if (choice.equalsIgnoreCase("p")) {
      System.out.printf("Total pages: %10d\n", library.totalPages());
      menu(input);
    } else {
      System.out.println("This command is not recognized. Please try again.");
      menu(input);
    }
  }

  /**
   * menu is the main menu for the application.
   * 
   * @param input The input channel for user input
   * @throws IOException
   */
  private static void menu(Scanner input) throws IOException {
    System.out.println("\nTo add a book to your library, press [A].");
    System.out.println("To see your shelves, press [S]");
    System.out.println("To see a specific shelf, press [G]");
    System.out.println("To mark a book read/ change the rating, press [R]");
    System.out.println("To see the number of books in the library, press [T]");
    System.out.println("To see the number of books read, press [F]");
    System.out.println("To see the number of pages read, press [P]");
    System.out.println("To exit the tracker, press [X]");
    String choice = input.nextLine();
    options(choice, input);
  }

  /**
   * addBook is the addition of a book to a shelf.
   * 
   * @param book  the list of parameters of which a book is to be created.
   * @param shelf the shelf to which the book is to be added to.
   */
  private static void addBook(String[] book, Shelf s) {
    String title = book[0];
    String author = book[1];
    int pages = Integer.parseInt(book[2]);
    String publisher = book[3];
    String series = book[4];
    int rating = Integer.parseInt(book[5]);

    s.addBook(new Book(title, author, pages, publisher, series, s.getName(), rating));
  }

  /**
   * loadFile parses the previously created library file.
   * 
   * @throws Exception
   */
  private static void loadFile(File file) throws Exception {
    Scanner inFile = new Scanner(file);
    String currGenre = "";
    while (inFile.hasNextLine()) {
      String line = inFile.nextLine();
      String[] pieces = line.split(" ");
      if (pieces[0].equals("Genre:")) {
        currGenre = "";
        String[] shelfName = line.split(" ");
        for (int i = 1; i < shelfName.length; i++) {
          currGenre += shelfName[i] + " ";
        }
        library.addShelf(new Shelf(currGenre.trim()));
      } else {
        Shelf s = library.getShelf(currGenre.trim());
        String[] book = line.split(",");
        addBook(book, s);
      }
    }
    inFile.close();
  }

  public static void main(String[] args) throws Exception {
    File newFile = new File("library.txt");
    if (!newFile.createNewFile()) {
      loadFile(newFile);
    }
    System.out.println("Welcome to the book-tracker!");
    menu(new Scanner(System.in));
  }
}