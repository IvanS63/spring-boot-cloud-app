package com.myapp.springboottester.service;

import com.myapp.springboottester.entity.Book;

import java.util.List;

/**
 * BookService.
 *
 * @author Ivan_Semenov
 */
public interface BookService {

    void add(Book book);

    Book findById(Integer bookId);

    void remove(Book book);

    void view(Integer id);

    List<Book> getAll();

    void markAsSold(Integer id);

    void increasePriceByAnnualPercent(Integer percent);

    void increaseAllPricesTwice();
}
