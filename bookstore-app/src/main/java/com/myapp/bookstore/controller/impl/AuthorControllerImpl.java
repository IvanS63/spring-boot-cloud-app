package com.myapp.bookstore.controller.impl;

import com.myapp.bookstore.controller.AuthorController;
import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Controller implementation.
 *
 * @author Ivan_Semenov
 */
@RestController
@RequestMapping("book-store/authors")
public class AuthorControllerImpl implements AuthorController {

    @Autowired
    private AuthorService authorService;

    @Override
    public List<Author> getTopSellingAuthorsByDateRange(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                                        @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        return authorService.getTopSellingAuthorsByDateRange(dateFrom, dateTo);
    }

    @Override
    public List<Author> getAuthorsFilteredByParams(String name, Integer amountOfBooks, Integer earnings, Integer amountOfSoldBooks) {
        return authorService.getAuthorsFilteredByParams(name, amountOfBooks, earnings, amountOfSoldBooks);
    }
}
