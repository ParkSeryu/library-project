package com.group.libraryapp.repository.Book;

import org.springframework.stereotype.Repository;

@Repository
public class BookMySqlRepository implements BookRepository {
    @Override
    public void saveBook() {
        System.out.println("BookMySqlRepository");
    }
}
