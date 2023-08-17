package PillSafe.PillSafeweb.Entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name="board_table")
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
public final class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NonNull
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @NonNull
    @Column(name = "title") //유일하고 최대 길이가 20.
    private String title;

    @NonNull
    @Column(name="content")
    private String content;

}