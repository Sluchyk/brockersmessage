package com.example.brockersmessage.model.repository;

import com.example.brockersmessage.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
