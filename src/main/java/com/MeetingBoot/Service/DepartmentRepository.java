package com.MeetingBoot.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MeetingBoot.entity.Department;;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
