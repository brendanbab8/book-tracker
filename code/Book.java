/**
 * Creates an instance of a book.
 * 
 * @author brendanbab8
 * @version 1.1
 */
public class Book implements Comparable<Book> {
  /** Various book characteristics. */
  private String title;
  private String author;
  private int pages;
  private String publisher;
  private String series;
  private String genre;
  private int rating;

  /**
   * The book construtor <br>
   * Precondition: If a book is not part of a series, the series keyword should be
   * "". <br>
   * Precondition: The rating should be in the range 0-10 inclusive.
   * 
   * @param title
   * @param author
   * @param pages
   * @param publisher
   * @param series
   * @param genre
   * @param rating
   */
  Book(String title, String author, int pages, String publisher, String series, String genre, int rating) {
    this.title = title;
    this.author = author;
    this.pages = pages;
    this.publisher = publisher;
    this.series = series;
    this.genre = genre;
    this.rating = rating;
  }

  /**
   * getTitle returns the book title.
   * 
   * @return the title of the book
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * getAuthor returns the book author.
   * 
   * @return the author of the book
   */
  public String getAuthor() {
    return this.author;
  }

  /**
   * getPages returns the book's number of pages.
   * 
   * @return the number of pages of the book
   */
  public int getPages() {
    return this.pages;
  }

  /**
   * getPublisher return the book publisher.
   * 
   * @return the publisher of the book
   */
  public String getPublisher() {
    return this.publisher;
  }

  /**
   * getSeries returns the book series.
   * 
   * @return the series of the book if present, <br>
   *         or "Standalone" if there is no series present
   */
  public String getSeries() {
    if (this.series == "") {
      return "Standalone";
    } else
      return this.series;
  }

  /**
   * getGenre returns the book genre.
   * 
   * @return the genre of the book
   */
  public String getGenre() {
    return this.genre;
  }

  /**
   * getRating returns the book rating.
   * 
   * @return the rating of the book
   */
  public int getRating() {
    return this.rating;
  }

  /**
   * compareTo returns the unique comparison for a book obejct. This sorts by
   * author based on standard lexicographical order
   * 
   * @param comp The book that is comapred against this book.
   * @return the result of the comaparison
   */
  public int compareTo(Book comp) {
    return this.author.compareToIgnoreCase(comp.author);
  }

  /**
   * toString returns the string equivalent of the book.
   */
  @Override
  public String toString() {
    if (rating != 0) {
      return String.format("Title: %-25s\nAuthor: %-15s\nRating: %-2d\n", title, author, rating);
    } else {
      return String.format("Title: %-25s\nAuthor: %-15s\nRating: Unread", title, author);
    }
  }

  /**
   * setRating is the changing of the rating.
   * 
   * @param rating the new rating for this book.
   */
  public void setRating(int rating) {
    this.rating = rating;
  }

  /**
   * textOutput is the String used in file writing
   * 
   * @return A formatted string containing this book's information
   */
  public String textOutput() {
    return title + "," + author + "," + Integer.toString(pages) + "," + publisher + "," + series + ","
        + Integer.toString(rating);
  }
}