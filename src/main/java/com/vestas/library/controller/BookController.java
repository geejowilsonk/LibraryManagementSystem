package com.vestas.library.controller;

import com.vestas.library.model.BookShelf;
import com.vestas.library.service.BookService;
import com.vestas.library.service.BorrowService;
import com.vestas.library.service.ReturnService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BorrowService borrowService;
    private final ReturnService returnService;
    public BookController(BookService bookService,BorrowService borrowService,ReturnService returnService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.returnService = returnService;
    }

    @GetMapping
    public List<BookShelf> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookShelf> getBookById(@PathVariable Long id) {
        Optional<BookShelf> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookShelf book, Principal principal) {
        if (!isOwner(principal)) {
            return ResponseEntity.status(403).body(" Access Denied: Only the owner can add books!");
        }
        BookShelf savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id, Authentication authentication) {
        if (!isOwner(authentication)) {  // Use Authentication to check roles
            return ResponseEntity.status(403).body(" Access Denied: Only the owner can delete books!");
        }
        boolean bookDeleted = bookService.deleteBook(id);
        return bookDeleted
                ? ResponseEntity.ok(" Book deleted successfully!")
                : ResponseEntity.status(404).body("Book not found!");
    }



    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, Authentication authentication) {
        if (!isClient(authentication)) {
            return ResponseEntity.status(403).body("Error: Only clients can borrow books!");
        }

        String message = borrowService.borrowBook(bookId);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, Authentication authentication) {
        if (!isClient(authentication)) {
            return ResponseEntity.status(403).body("Error : Only clients can return books!");
        }
        String message = returnService.returnBook(bookId);
        return ResponseEntity.ok(message);
    }

    private boolean isClient(Principal principal) {
        return principal != null && principal.getName().equals("client");
    }

    private boolean isOwner(Principal principal) {
        return principal != null && principal.getName().equals("owner");
    }

}
