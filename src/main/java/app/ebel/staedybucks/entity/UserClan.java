package app.ebel.staedybucks.entity;


import app.ebel.staedybucks.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.MEMBER;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

}
