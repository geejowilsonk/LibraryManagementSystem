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
public class ReturnService {

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public ReturnService(BookRepository bookRepository, BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public String returnBook(Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String clientUsername = authentication.getName();

        Optional<BorrowRecord> borrowRecordOptional = borrowRecordRepository
                .findByBorrowedBy(clientUsername)
                .stream()
                .filter(record -> record.getBook().getId().equals(bookId) && record.getReturnDate() == null)
                .findFirst();

        if (borrowRecordOptional.isEmpty()) {
            return " Error : You haven't borrowed this book or it's already returned!";
        }

        BorrowRecord borrowRecord = borrowRecordOptional.get();
        borrowRecord.setReturnDate(LocalDateTime.now());
        borrowRecordRepository.save(borrowRecord);

        BookShelf book = borrowRecord.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return "Book returned successfully!";
    }
}
