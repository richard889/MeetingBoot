package com.MeetingBoot.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MeetingBoot.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	public List<Book> findByNameEndsWith(String name);
}
