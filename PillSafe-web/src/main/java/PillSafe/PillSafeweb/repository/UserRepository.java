package PillSafe.PillSafeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByGoogleId(String googleId);
    User save(User user); //회원을 저장하면 저장된 회원이 반환된다.
    Optional<User> findById(Long id); //id로 회원을 찾기
//    Optional<User> findByName(String name);
    List<User> findAll();
}