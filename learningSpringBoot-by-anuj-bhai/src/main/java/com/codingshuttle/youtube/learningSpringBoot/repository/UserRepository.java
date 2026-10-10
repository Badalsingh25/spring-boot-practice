package com.codingshuttle.youtube.learningSpringBoot.repository;

import com.codingshuttle.youtube.learningSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
