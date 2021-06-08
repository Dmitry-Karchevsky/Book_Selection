package bookselectionapp.adapters;

import bookselectionapp.entities.Book;

import java.util.List;

public class BookListAdapter {
    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
