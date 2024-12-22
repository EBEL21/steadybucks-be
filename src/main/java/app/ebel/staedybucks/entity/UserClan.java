package app.ebel.staedybucks.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static app.ebel.staedybucks.entity.UserClan.UserRole.MEMBER;

@Entity
@Table(name = "user_clan")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserClan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clan_id")
    private Clan clan;

    @Enumerated(EnumType.STRING)
    private UserRole role = MEMBER;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

    public enum UserRole {
        CAPTAIN,
        MANAGER,
        MEMBER
    }
}
