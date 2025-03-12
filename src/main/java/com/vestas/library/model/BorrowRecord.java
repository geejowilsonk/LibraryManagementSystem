package com.vestas.library.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookShelf book;

    @Column(nullable = false)
    private String borrowedBy;

    @Column(nullable = false)
    private LocalDateTime borrowDate;

    @Column(nullable = true)
    private LocalDateTime returnDate;

    public BorrowRecord() {}

    public BorrowRecord(BookShelf book, String borrowedBy, LocalDateTime borrowDate) {
        this.book = book;
        this.borrowedBy = borrowedBy;
        this.borrowDate = borrowDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public BookShelf getBook() { return book; }
    public void setBook(BookShelf book) { this.book = book; }
    public String getBorrowedBy() { return borrowedBy; }
    public void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }
    public LocalDateTime getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDateTime borrowDate) { this.borrowDate = borrowDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
}
