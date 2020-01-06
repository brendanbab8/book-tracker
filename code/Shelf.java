
/**
 * Creates an instance of a library.
 */
import java.util.ArrayList;

public class Shelf {
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
}