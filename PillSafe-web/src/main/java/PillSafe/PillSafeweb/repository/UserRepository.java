package PillSafe.PillSafeweb.repository;


import PillSafe.PillSafeweb.Entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void flush();
    PillSafe.PillSafeweb.Entity.User findByGoogleId(String googleId);
    User findByEmail(String email);
    List<User> findByAgeGreaterThan(int age);
    List<User> searchUsersByNameKeyword(String keyword);
    List<User> findAll();
    List<User> findAllById(Iterable<Long> ids);
    long count();
    void deleteById(Long id);
    void deleteAll();

}