package app.ebel.staedybucks.entity;

import app.ebel.staedybucks.enums.CreatorType;
import app.ebel.staedybucks.enums.TradingType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "interest")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private BigDecimal capturedPrice;

    @Column(name = "target_price")
    private BigDecimal targetPrice;

    @Column(name = "trading_type")
    @Enumerated(EnumType.STRING)
    private TradingType type;

}
