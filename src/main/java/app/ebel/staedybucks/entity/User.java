package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true, length = 30)
    private String loginId;
    @Column(name="password", nullable = false, length = 30)
    private String password;
    @Column(name = "nickname", nullable = false, unique = true, length = 15)
    private String nickname;


    // Joined Column
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name="userclan_id", referencedColumnName = "id")
    private List<UserClan> registeredClan;

    @OneToMany(mappedBy = "createdUser", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name="interest_id", referencedColumnName = "id")
    private List<Interest> interests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterestFollow> interestFollows;
}
