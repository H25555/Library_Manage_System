package org.example.service.impls;

import org.example.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getListBook();
    Book getBookDetail(long bookid);
    void add(Book newbook);
    void delete(long bookid);
    void edit(long bookid, Book updateBook);
    void changeBookStatus(long bookid);
    boolean checkBookStatus(long bookid);
}
