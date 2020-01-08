
/**
 * Creates an instance of a library.
 */
import java.util.ArrayList;

public class Library {
  private ArrayList<Shelf> shelves;

  public Library() {
    this.shelves = new ArrayList<>();
  }

  /**
   * getShelves is all the shelves in this library.
   * 
   * @return A list of all shelves.
   */
  public ArrayList<Shelf> getShelves() {
    return this.shelves;
  }

  /**
   * toString is the string equivalent of the library
   * 
   * @return A formatted string of the shelves in the library.
   */
  @Override
  public String toString() {
    String shelfStr = "Shelves in this library:\n";
    for (Shelf s : shelves) {
      shelfStr += s.getName() + "\n";
    }
    return shelfStr;
  }

  /**
   * getShelfIndex finds the shelf in the library based on name.
   * 
   * @param name The name of the shelf.
   * @return the index of the requested shelf, or -1 if not found.
   */
  private int getShelfIndex(String name) {
    for (int i = 0; i < shelves.size(); i++) {
      Shelf s = shelves.get(i);
      if (s.getName().equalsIgnoreCase(name)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * getShelf is the Shelf that matches the given name.
   * 
   * @param name The name of the Shelf
   * @return The shelf that matches the given name.
   * @throws Exception if the shelf is not found.
   */
  public Shelf getShelf(String name) throws Exception {
    try {
      return shelves.get(getShelfIndex(name));
    } catch (Exception e) {
      Exception user = new Exception("Shelf not found!");
      throw user;
    }
  }

  /**
   * addShelf is the addition of a new shelf to the library
   * 
   * @param shelf the shelf to be added.
   */
  public void addShelf(Shelf shelf) {
    shelves.add(shelf);
  }
}