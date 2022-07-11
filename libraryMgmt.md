https://drive.google.com/file/d/172ZnP1P0Gp3X5edtqjIMhlLRMWBVdaBv/view

class Library {

	String name;
	Address location;
	List<BookItem> books;
}

class Address {

	int pinCode //ZipCode
	String street;
	String city;
	String state;
	String country;
}


class Book {

	String uniqueIdNumber;
	String title;
	List<Author> authors;
	BookType bookType;
}

class BookItem extends Book {    (decoupling , different author books)

	String barcode;
	Date publicationDate;
	Rack rackLocation;
	BookStatus bookStatus;
	BookFormat bookFormat;
	Date issueDate;
}

public enum BookType {

	SCI_FI, ROMANTIC, FANTASY, DRAMA;
}


public enum BookFormat {

	HARDCOVER, PAPERBACK, NEWSPAPER, JOURNAL;
}

public enum BookStatus {

	ISSUED, AVAILABLE, RESERVED, LOST;
}

class Rack {

	int number;
	String locationId;

}

class Person {               (defining actors)

	String firstName;
	String lastName;

}

class Author extends Person {        (list down book by all author)

	List<Book> booksPublished;

}

class SystemUser extends Person {

	String Email;
	String phoneNumber;
	Account account;
}

class Account {

	String userName;
	String password;
	int accountId;
}


class Member extends SystemUsers { (how many books checked out by an member)

	int totalBookCheckedOut;

	Search searchObj;
	BookIssueService issueService;

}

class Librarian extends SystemUsers {

	Search searchObj;
	BookIssueService issueService;

	public void addBookItem(BookItem bookItem);
	public BookItem deleteBookItem(String barcode); 
	public BookItem editBookItem(BookItem bookItem);
}


class Search {

	public List<BookItem> geBookByTitle(String title);
	public List<BookItem> geBookByAuthor(Author author);
	public List<BookItem> geBookByType(BookType bookType);
	public List<BookItem> geBookByPublicationDate(Date publicationDate);

}

class BookIssueService {

	Fine fine;   (for calcualtion of fine)

	public BookReservationDetail getReservationDetail(BookItem book);   (we need to check whether that book is renewed or not -> issue book or not)

	public void updateReservationDetail(BookReservationDetail bookReservationDetail);

	public BookReservationDetail reserveBook(BookItem book, SystemUser user);

	public BookIssueDetail issueBook(BookItem book, SystemUser user);

	// it will internaly call the issueBook function after basic validations
	public BookIssueDetail renewBook(BookItem book, SystemUser user); 

	public void returnBook(BookItem book, SystemUser user);

}

class BookLending {

	BookItem book;
	Date startDate;
	SystemUser user;
}

class BookReservationDetail extends BookLending {

	ReservationStatus reservationStatus;     ReservationStatus is an enum ->

}

class BookIssueDetail extends BookLending {

	Date dueDate;

}

class Fine {

	Date fineDate;
	BookItem book;
	SystemUser user;

	public double calculateFine(int days);
}
