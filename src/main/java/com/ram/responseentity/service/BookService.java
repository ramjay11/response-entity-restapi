package com.ram.responseentity.service;

import java.util.List;

import com.ram.responseentity.model.Book;

public interface BookService {

	void addBook(Book book);
	List<Book> getBooksByAuthor(String author);
	List<Book> getBooksByCategory(String category);
	Book getBookById(int bookId);
}
