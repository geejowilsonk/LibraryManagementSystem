package com.vestas.library.repository;

import com.vestas.library.model.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookShelf, Long> {
    Optional<BookShelf> findByTitle(String title);
}
