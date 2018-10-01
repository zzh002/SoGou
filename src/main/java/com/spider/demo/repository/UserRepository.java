package com.spider.demo.repository;

import com.spider.demo.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {
    User findByUsername(String username);
}
