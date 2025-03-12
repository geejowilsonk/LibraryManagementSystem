package com.vestas.library.service;

import com.vestas.library.model.BookShelf;
import com.vestas.library.model.BorrowRecord;
import com.vestas.library.repository.BookRepository;
import com.vestas.library.repository.BorrowRecordRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BorrowService {

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowService(BookRepository bookRepository, BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public String borrowBook(Long bookId) {
        Optional<BookShelf> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isEmpty()) {
            return " Book not found!";
        }

        BookShelf book = optionalBook.get();

        if (book.getAvailableCopies() <= 0) {
            return " Book is not available!";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String clientUsername = authentication.getName(); // Get logged-in user's name

        BorrowRecord borrowRecord = new BorrowRecord(book, clientUsername, LocalDateTime.now());
        borrowRecordRepository.save(borrowRecord);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return " Book borrowed successfully!";
    }

}
