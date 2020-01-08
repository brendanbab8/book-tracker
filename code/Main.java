
/**
 * Runs the book-tracker
 * 
 * @author brendanbab8
 * @version 1.0
 */

import java.io.IOException;
import java.util.Scanner;

public class Main {
  /** The library object where all books are stored. */
  private static Library library = new Library();

  /**
   * checkPages checks for a valid page number. Pages must be positive (>=0).
   * 
   * @param pages
   * @return the number of pages.
   */
  public static int checkPages(int pages, Scanner input) {
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
  public static int checkRating(int rating, Scanner input) {
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
  public static void addBook(Scanner input) {
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
   * menu is the main menu for the application.
   * 
   * @param input The input channel for user input
   */
  public static void menu(Scanner input) {
    System.out.println("\nTo add a book to your library, press [A].");
    System.out.println("To see your shelves, press [S]");
    System.out.println("To see a specific shelf, press [G]");
    System.out.println("To exit the tracker, press [X]");
    String choice = input.nextLine();
    if (choice.equalsIgnoreCase("a")) {
      addBook(input);
      System.out.println("Book added successfully!");
      input.nextLine();
      menu(input);
    } else if (choice.equalsIgnoreCase("x")) {
      input.close();
      System.out.print("Goodbye!\n");
    } else if (choice.equalsIgnoreCase("s")) {
      System.out.println(library.toString() + "\n");
      menu(input);
    } else if (choice.equalsIgnoreCase("g")) {
      System.out.println("Enter the name of the shelf you want to view: ");
      choice = input.nextLine();

      try {
        Shelf s = library.getShelf(choice);
        System.out.println(s.toString() + "\n");
      } catch (Exception e) {
        System.out.println("Shelf not found. Please try again.");
      }

      menu(input);
    } else {
      System.out.println("This command is not recognized. Please try again.");
      menu(input);
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Welcome to the book-tracker!");
    menu(new Scanner(System.in));
  }
}