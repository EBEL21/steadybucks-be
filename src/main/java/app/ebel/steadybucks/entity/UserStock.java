package app.ebel.steadybucks.entity;

import app.ebel.steadybucks.entity.eid.UserStockId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "user_stock")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStock {

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

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}
