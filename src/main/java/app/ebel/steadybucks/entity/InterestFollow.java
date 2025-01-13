package app.ebel.steadybucks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "interest_follow",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "interest_id"})
)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "followed_at")
    @Temporal(TemporalType.DATE)
    private LocalDate followedAt;

    @Column(name = "closed_at")
    @Temporal(TemporalType.DATE)
    private LocalDate closedAt;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "interest_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Interest interest;
}
