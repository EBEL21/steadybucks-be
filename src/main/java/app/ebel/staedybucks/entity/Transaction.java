package app.ebel.staedybucks.entity;

import app.ebel.staedybucks.entity.eid.UserStockId;
import app.ebel.staedybucks.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @EmbeddedId
    UserStockId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @MapsId("stockCode")
    @JoinColumn(name = "stock_code", referencedColumnName = "code")
    private Stock stock;

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

}
