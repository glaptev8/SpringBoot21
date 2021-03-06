package edu.school21.springboot.repository;

import edu.school21.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);

  User findByActivateCode(String code);
}
