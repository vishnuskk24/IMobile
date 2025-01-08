package com.infy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long > {

	Optional<Users> findByMobileNumber(Long mobileNumber);

	Optional<Users> findByUserId(String userId);

	Optional<Users> findByEmail(String mail);

//	void save(Users newUser);

}
