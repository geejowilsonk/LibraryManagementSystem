package com.vestas.library.service;

import com.vestas.library.model.BookShelf;
import com.vestas.library.model.BorrowRecord;
import com.vestas.library.repository.BookRepository;
import com.vestas.library.repository.BorrowRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public BookService(BookRepository bookRepository,BorrowRecordRepository borrowRecordRepository ) {
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public List<BookShelf> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookShelf> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public BookShelf addBook(BookShelf book) {
        return bookRepository.save(book);
    }

    @Transactional
    public boolean deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            return false;
        }
        bookRepository.deleteById(bookId);
        return true;
    }
}
