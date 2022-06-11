package LibrarySystem;

import java.util.Date;
import java.util.List;

public class Library {
    Address address;
    String id;
    String name;
    List<Book> bookList;
}

class Address {
    String address;
    String zipcode;
    String city;
    String latitude;
    String longitude;
}

class Book {
    String id;
    String title;
    List<Author> authors;
    BookType bookType;
}

class BookItem extends Book {
    String barcode;
    Date publicationDate;
    Rack rackLocation;
    BookStatus bookStatus;
    BookFormat bookFormat;
    Date issueDate;
}

enum BookType {
    SCI_FI, ROMANTIC, FANTASY, DRAMA;
}

enum BookFormat {
    HARDCOVER, PAPERBACK, NEWSPAPER, JOURNAL;
}

enum BookStatus {
    ISSUED, AVAILABLE, RESERVED, LOST;
}

class Rack {
    int rackId;
    String locationId;
}

class Person {
    String firstName;
    String lastName;
}

class Author extends Person {
    List<Book> booksPublished;
}

class SystemUser extends Person {
    String email;
    String phoneNumber;
    Account account;
}

class Member extends SystemUser {
    int totalBooksCheckedOut;
    Search searchObj;
    BookIssueService bookIssueService;
}

class Librarian extends SystemUser {
    Search searchObj;
    BookIssueService bookIssueService;

    public void addBookItem(BookItem bookItem){}
    public void editBookItem(BookItem bookItem){}
    public void deleteBookItem(BookItem bookItem){}
}

class Account {
    String userName;
    String passWord;
    String accountId;
}

class Search {
    public List<BookItem> getBookByTitle(String title){ return null; }
    public List<BookItem> getBookByAuthor(Author author){ return null; }
    public List<BookItem> getBookByType(BookType bookType){ return null; }
    public List<BookItem> getBookByPublicationDate(Date publicationDate){ return null; }
}

class BookIssueService {
    FineService fineService;
    public BookReservationDetail getReservationDetail(BookItem book){return null;}
    public void updateReservationDetail(BookReservationDetail bookReservationDetail){}
    public BookReservationDetail reserveBook(BookItem bookItem, SystemUser user){return null;}
    public BookIssueDetail issueBook(BookItem bookItem, SystemUser user){return null;}
    public void returnBook(BookItem bookItem, SystemUser user){}
}

class BookLending {
    BookItem bookItem;
    Date startDate;
    SystemUser User;
}

class BookReservationDetail extends BookLending {
    ReservationStatus reservationStaus;
}

class BookIssueDetail extends BookLending {
    Date dueDate;
}

class FineService {
    public Fine calculateFine(SystemUser user, BookItem bookItem, int days) {return null;}
}

class Fine {
    BookItem bookItem;
    Date fineDate;
    SystemUser user;
    Double fineAmount;
}

class ReservationStatus {
    Date reserveDate;
}