package com.service;

import com.vestas.library.model.BookShelf;
import com.vestas.library.repository.BookRepository;
import com.vestas.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceSpec {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void AddBook() {
        BookShelf bookToAdd = new BookShelf("Book1", "Author1", 1);
        BookShelf savedBook = new BookShelf("Book1", "Author1", 1);

        when(bookRepository.save(bookToAdd)).thenReturn(savedBook);

        BookShelf result = bookService.addBook(bookToAdd);

        assertEquals("Book1", result.getTitle());
        assertEquals("Author1", result.getAuthor());
        assertEquals(1, result.getTotalCopies());
    }
}