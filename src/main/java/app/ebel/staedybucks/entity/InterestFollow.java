package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "interest_follow")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InterestFollow {
    @Id
    private Long id;

    @Column(name = "followed_at")
    @Temporal(TemporalType.DATE)
    private LocalDate followedAt;

    @Column(name = "closed_at")
    @Temporal(TemporalType.DATE)
    private LocalDate closedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Interest interest;
}
