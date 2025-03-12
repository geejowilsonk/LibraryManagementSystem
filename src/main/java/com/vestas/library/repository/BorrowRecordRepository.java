package com.vestas.library.repository;

import com.vestas.library.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByBorrowedBy(String borrowedBy);
    boolean existsByBook_Id(Long bookId);
}