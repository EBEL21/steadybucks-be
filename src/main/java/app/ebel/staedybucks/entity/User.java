package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;
    @Column(name="email", nullable = false, unique = false)
    private String password;
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;


    // Joined Column
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="userclan_id", referencedColumnName = "id")
    private List<UserClan> registeredClan;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="interest_id", referencedColumnName = "id")
    private List<Interest> interests;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="interest_follow_id", referencedColumnName = "id")
    private List<InterestFollow> interestFollows;
}
