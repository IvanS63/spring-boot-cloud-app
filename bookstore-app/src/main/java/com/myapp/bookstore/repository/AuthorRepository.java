package com.myapp.bookstore.repository;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * DAO layer for operating with {@link Author} entity.
 *
 * @author Ivan_Semenov
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    String TOP_AUTHORS_BY_MONTH_SOLD_BOOK_QUERY = "SELECT a.id, a.name, SUM(b.price) as month_total_price " +
            "FROM author a " +
            "INNER JOIN book b ON b.author_id=a.id " +
            "WHERE b.sold_date >:dateFrom AND b.sold_date < :dateTo " +
            "GROUP BY a.id " +
            "ORDER BY month_total_price DESC " +
            "LIMIT 5";

    String AUTHORS_FILTERED_BY_PARAMS_QUERY = "SELECT * FROM author a " +
            "WHERE (:name IS NULL OR a.name LIKE %:name%) " +
            "AND (:amountOfBooks IS NULL OR (SELECT COUNT(b.id) FROM book b WHERE b.author_id = a.id) >= :amountOfBooks) " +
            "AND (:earnings IS NULL  OR (SELECT SUM(b.price) FROM book b WHERE b.author_id=a.id AND b.sold_date IS NOT NULL) >= :earnings) " +
            "AND (:amountOfSoldBooks IS NULL OR (SELECT COUNT(b.id) FROM book b WHERE b.author_id=a.id AND b.sold_date IS NOT NULL) >= :amountOfSoldBooks)";

    @Query("SELECT b FROM Book b WHERE b.author.id = ?1 AND b.soldDate IS NOT NULL")
    List<Book> getSoldBooks(Integer authorId);

    @Query(value = TOP_AUTHORS_BY_MONTH_SOLD_BOOK_QUERY,
            nativeQuery = true)
    List<Author> getTopSellingAuthorsByDateRange(
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo") Date dateTo);

    Author findByName(String name);


    @Query(value = AUTHORS_FILTERED_BY_PARAMS_QUERY,
            nativeQuery = true)
    List<Author> getAuthorsFilteredByParams(
            @Param("name") String name,
            @Param("amountOfBooks") Integer amountOfBooks,
            @Param("earnings") Integer earnings,
            @Param("amountOfSoldBooks") Integer amountOfSoldBooks
    );
}
