package app.ebel.staedybucks.entity;

import app.ebel.staedybucks.entity.eid.UserStockId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_stock")
@Setter
@Getter
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

    @Column(name = "average_price", nullable = false)
    private float averagePrice;

    @Column(name = "quantity", nullable = false)
    private float quantity;

}
