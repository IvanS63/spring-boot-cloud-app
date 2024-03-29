package com.myapp.bookstore.repository;

import com.myapp.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Book DAO layer.
 *
 * @author Ivan_Semenov
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET price = price + price*:percent/100", nativeQuery = true)
    void increasePriceByAnnualPercent(@Param("percent") Integer percent);
}
