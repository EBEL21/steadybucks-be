package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clan")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 15)
    private String name;

    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

    @OneToMany(mappedBy = "clan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserClan> clanMembers;

    @OneToMany(mappedBy = "createdClan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interest> interests;

}
