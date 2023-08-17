package PillSafe.PillSafeweb.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import jakarta.persistence.*;

@Entity
@Table(name="user_table")
@Builder
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User {
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "user_googleId") //유일하고 최대 길이가 20.
    private String googleId;
    @Column(name="user_username")
    private String name;
    @Column(name="user_email")
    private String email;
}