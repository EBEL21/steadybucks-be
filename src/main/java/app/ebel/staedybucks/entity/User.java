package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true, length = 30)
    @NotBlank(message = "Login ID entry is required.")
    @Size(min = 5, max=30)
    private String loginId;

    @Column(name="password", nullable = false, length = 30)
    @NotBlank(message = "Password entry is required.")
    @Size(min = 5, max=30)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true, length = 15)
    @NotBlank(message = "Nickname is required")
    @Size(min = 2, max = 15)
    @Pattern(regexp = "^[A-Za-z0-9가-힣]+$")
    private String nickname;


    // Joined Column
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @JoinColumn(name="userclan_id", referencedColumnName = "id")
    private Set<UserClan> registeredClan;

    @OneToMany(mappedBy = "createdUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @JoinColumn(name="interest_id", referencedColumnName = "id")
    private List<Interest> interests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InterestFollow> interestFollows;
}
