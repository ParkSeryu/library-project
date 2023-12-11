package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.Book.BookMemoryRepository;
import com.group.libraryapp.repository.Book.BookMySqlRepository;
import com.group.libraryapp.repository.Book.BookRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(@Qualifier("test") BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook() {
        bookRepository.saveBook();
    }
}
