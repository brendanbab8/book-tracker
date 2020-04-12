
/**
 * Creates an instance of a library.
 * 
 * @author brendanbab8
 * @version 1.2
 */
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Library {
  private ArrayList<Shelf> shelves;
  private Book currentRead;

  public Library() {
    this.shelves = new ArrayList<>();
    this.currentRead = new Book("", "", 0, "", "", "", 0);
  }

  /**
   * getCurrent is the book being currently read.
   * 
   * @return the book denoted as currently read.
   */
  public Book getCurrent() {
    return this.currentRead;
  }

  /**
   * setCurrent sets the book being currently read.
   * 
   * @param book The title of the book to be set
   */
  public void setCurrent(String book) {
    for (Shelf s : shelves) {
      Book b = s.findBook(book);
      if (b != null) {
        currentRead = b;
      }
    }
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
   * toString is the string equivalent of the library shelves.
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
   * viewLibrary is the string equivalent of the entire library.
   * 
   * @return A formatted string of all library contents.
   */
  public String viewLibrary() {
    String message = "Currently Reading:\n\n";
    if (currentRead.getTitle().length() < 1) {
      message += "None\n\n";
    } else {
      message += String.format("Title: %-25s\nAuthor: %-15s\n\n", currentRead.getTitle(), currentRead.getAuthor());
    }

    message += toString();

    return message;
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

  /**
   * writeToFile converts this library to a text file stored in the project
   * folder.
   * 
   * @throws IOException
   */
  public void writeToFile() throws IOException {
    BufferedWriter outFile = new BufferedWriter(new FileWriter("library.txt"));
    for (Shelf s : shelves) {
      outFile.write("Genre: " + s.getName() + "\n");
      for (Book b : s.getShelf()) {
        outFile.write(b.textOutput() + "\n");
      }
    }
    outFile.write("Current: \n" + currentRead.textOutput());

    outFile.close();
  }

  /**
   * booksRead counts the number of books that have been rated. A rating of zero
   * means that the book is unread, and thus will not be counted.
   * 
   * @return The number of books read
   */
  public int booksRead() {
    int total = 0;

    for (Shelf s : shelves) {
      for (Book b : s.getShelf()) {
        if (b.getRating() > 0) {
          total++;
        }
      }
    }

    return total;
  }

  /**
   * totalBooks counts the number of books in the library.
   * 
   * @return The number of books in the library.
   */
  public int totalBooks() {
    int total = 0;
    for (Shelf s : shelves) {
      total += s.getShelf().size();
    }
    return total;
  }

  /**
   * totalPages counts the number of pages read.
   * 
   * @return The number of pages read in the library.
   */
  public int totalPages() {
    int total = 0;

    for (Shelf s : shelves) {
      for (Book b : s.getShelf()) {
        if (b.getRating() > 0) {
          total += b.getPages();
        }
      }
    }

    return total;
  }
}