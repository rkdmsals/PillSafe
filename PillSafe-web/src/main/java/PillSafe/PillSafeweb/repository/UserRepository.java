package PillSafe.PillSafeweb.repository;


import PillSafe.PillSafeweb.Entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void flush();
    PillSafe.PillSafeweb.Entity.User findByGoogleId(String googleId);
}