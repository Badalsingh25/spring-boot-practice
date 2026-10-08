package com.codingshuttle.youtube.LearningSpringboot.repository;

import com.codingshuttle.youtube.LearningSpringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
