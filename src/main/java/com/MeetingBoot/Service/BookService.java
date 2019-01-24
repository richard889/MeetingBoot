package com.MeetingBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingBoot.entity.Book;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll(){		
		return bookRepository.findAll();
	}
	
	public Book findOne(long id) {
		return bookRepository.findById(id).get();
	}
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}	

	public void delete(long id) {
		bookRepository.deleteById(id);
	}
	
	public List<Book> findByNameEndsWith(String name){		
		return bookRepository.findByNameEndsWith(name);
	}	
	
}
