import PillSafe.PillSafeweb.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환된다.
    Optional<Member> findById(Long id); //id로 회원을 찾기
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
