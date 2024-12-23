package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clan")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 15)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="userclan_id", referencedColumnName = "id")
    private List<UserClan> clanMembers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="interest_id", referencedColumnName = "id")
    private List<Interest> interests;

}
