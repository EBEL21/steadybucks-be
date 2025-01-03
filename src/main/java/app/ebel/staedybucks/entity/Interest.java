package app.ebel.staedybucks.entity;

import app.ebel.staedybucks.enums.CreatorType;
import app.ebel.staedybucks.enums.TradingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "interest")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Interest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creator_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CreatorType creatorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clan_id")
    private Clan createdClan;

    @Column(name = "added_at", nullable = false)
    private LocalDate joinedAt;

    @Column(name = "captured_at", nullable = false)
    private LocalDate capturedAt;

    @Column(name = "closed_at")
    private LocalDate closedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_code", nullable = false, referencedColumnName = "code")
    private Stock stock;

    @Column(name = "captured_price", nullable = false)
    private float capturedPrice;

    @Column(name = "target_price")
    private float targetPrice;

    @Column(name = "trading_type")
    @Enumerated(EnumType.STRING)
    private TradingType type;

}
