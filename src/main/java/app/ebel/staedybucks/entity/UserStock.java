package app.ebel.staedybucks.entity;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_code", referencedColumnName = "code")
    private Stock stock;

    @Column(name = "average_price", nullable = false)
    private float averagePrice;

    @Column(name = "quantity", nullable = false)
    private float quantity;

}
