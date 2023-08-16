package PillSafe.PillSafeweb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name="member_table")
@Builder
@Getter
@Setter
public class User {
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length=20) //유일하고 최대 길이가 20.
    private String googleId;
    @Column(name="user_googleId")
    private String username;
    @Column(name="user_email")
    private String email;
}