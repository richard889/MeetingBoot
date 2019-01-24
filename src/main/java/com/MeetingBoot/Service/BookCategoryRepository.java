package com.MeetingBoot.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MeetingBoot.entity.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>{
}
