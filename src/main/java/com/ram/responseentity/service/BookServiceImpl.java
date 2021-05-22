package com.ram.responseentity.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ram.responseentity.model.Book;


@Service
public class BookServiceImpl implements BookService {

	@Override
	public void addBook(Book book) {
		System.out.println(book);
		
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		return getBookList()
				.stream()
				.filter((book) -> book.getAuthor().equals(author))
				.collect(Collectors.toList());
	}

	@Override
	public Book getBookById(int bookId) {
		return getBookList()
				.stream()
				.filter((book) -> book.getBookid() == bookId)
				.findAny()
				.orElse(new Book());
	}
	
	@Override
	public List<Book> getBooksByCategory(String category) {
		return getBookList()
				.stream()
				.filter((book) -> book.getCategory().equals(category))
				.collect(Collectors.toList());
				
	}
	
	// Hard coded values of Book instead of coming from a database
	private List<Book> getBookList() {
		return Arrays.asList(new Book("Java", "Kathy", "Tech", 10),
				new Book("Spring", "Rod", "Tech", 11),
				new Book("Ferrari", "Diane", "Fiction", 12));
	}
}
