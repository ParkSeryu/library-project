package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;


    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 책 정보를 가져온다.
        // 2. 대출기록 정보를 확인해서 대출중인지 확인한다.
        // 3. 만약에 확인했는데 대출 중이면 예외를 발생시킨다.

        //My 1
//        boolean isExist = bookRepository.existsByName(request.getBookName());
//        if (!isExist) {
//            throw new IllegalArgumentException("책이 없습니다.");
//        }

        //BP 1
        Book book = bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);

        //MY 2
//        UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByBookName(request.getBookName());
//
//        if(userLoanHistory != null){
//            if(userLoanHistory.isReturn()){
//                throw new IllegalArgumentException("이미 대출중인 책입니다.");
//            }
//        }

        //BP 2
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출중인 책입니다.");
        }


        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        user.returnBook(request.getBookName());


    }
}
