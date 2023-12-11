package com.group.libraryapp.repository.Book;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("test")
public class BookMemoryRepository implements BookRepository {
//    private final List<Book> books = new ArrayList<Book>();

    public void saveBook() {
//        books.add(new Book());
        System.out.println("BookMemoryRepository");
    }
}
