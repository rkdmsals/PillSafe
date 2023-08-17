package PillSafe.PillSafeweb.Entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name="user_table")
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_googleId") //유일하고 최대 길이가 20.
    private String googleId;
    @Column(name="user_username")
    private String name;
    @Column(name="user_email")
    private String email;
}