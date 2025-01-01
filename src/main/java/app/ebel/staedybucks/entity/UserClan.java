package app.ebel.staedybucks.entity;


import app.ebel.staedybucks.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "user_clan",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "clan_id"})
)
public class UserClan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clan_id", nullable = false)
    private Clan clan;

    @Column(name = "role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.MEMBER;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

}
