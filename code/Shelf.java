
/**
 * Creates an instance of a library.
 * 
 * @author brendanbab8
 * @version 1.0
 */
import java.util.ArrayList;

public class Shelf implements Comparable<Shelf> {
  private ArrayList<Book> shelf;
  private String name;

  /**
   * An instance of a library
   * 
   * @param name
   */
  public Shelf(String name) {
    this.name = name;
    this.shelf = new ArrayList<>();
  }

  /**
   * getShelf is the list of books associated with the shelf.
   * 
   * @return the books on the shelf.
   */
  public ArrayList<Book> getShelf() {
    return this.shelf;
  }

  /**
   * getName is the name of the shelf.
   * 
   * @return the shelf name
   */
  public String getName() {
    return this.name;
  }

  /**
   * compareTo compares two Shelf objects. This sorts by name based on standard
   * lexicographical order
   * 
   * @param comp The shelf that is comapred against this shelf.
   * @return the result of the comaparison
   */
  @Override
  public int compareTo(Shelf comp) {
    return this.name.compareToIgnoreCase(comp.name);
  }

  /**
   * toString is the string eqiuvalent of the shelf.
   * 
   * @return The formatted string of this shelf
   */
  @Override
  public String toString() {
    String books = "";
    for (Book b : this.shelf) {
      books += b.toString() + "\n";
    }
    return String.format("Shelf title: %-20s \n Books in this shelf:\n" + books, this.name);
  }

  /**
   * addBook is the addition of a book to this shelf.
   * 
   * @param book The book to be added.
   */
  public void addBook(Book book) {
    this.shelf.add(book);
  }

  /**
   * findBook is the Book based on the title
   * 
   * @param book The book title to be searched for
   * @return The book with the same title as the given argument
   */
  public Book findBook(String book) {
    for (Book b : shelf) {
      if (b.getTitle().equalsIgnoreCase(book)) {
        return b;
      }
    }

    return null;
  }
}