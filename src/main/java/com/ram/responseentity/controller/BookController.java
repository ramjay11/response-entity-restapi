package com.ram.responseentity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ram.responseentity.model.Book;
import com.ram.responseentity.service.BookService;

@RestController
@RequestMapping("book-restapi")
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping("/greet")
	public ResponseEntity<String> sayHello() {
		String msg = "Welcome to Book App";
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Online Book Application");
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	@PostMapping("/books")
	public ResponseEntity<Void> addBook(@RequestBody Book book) {
		bookService.addBook(book);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Adding one book");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
		//return ResponseEntity.ok().build()
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int bookId) {
		Book book = bookService.getBookById(bookId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Getting book by id");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(book);
	}
	
	@GetMapping("/books-by-author/{author}")
	public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
		List<Book> bookList = bookService.getBooksByAuthor(author);
		return ResponseEntity.ok(bookList);
	}
	/*The @RequestParam is used to extract query parameters while @PathVariable is used to extract data right from the URI.
	@RequestParam is more useful on a traditional web application where data is mostly passed in the query abatements while
	@PathVariable is more suitable for RESTful web services where URL contains values, like http://localhost:8080/book/9783827319333, 
	here data, which is ISBN number is part of URI.*/
	@GetMapping("/books-by-category")
	public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam("category") String category) {
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "List of books by category");
		header.add("type", "book object");
		List<Book> bookList = bookService.getBooksByCategory(category);
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(bookList);
	}
	
}
