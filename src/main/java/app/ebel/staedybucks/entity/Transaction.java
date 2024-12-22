package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public enum TransactionType {
        BUY,
        SELL
    }

    @Column(name = "quantity", nullable = false)
    private float quantity;

    @Column(name = "price_per_unit", nullable = false)
    private float pricePerUnit;

    @Column(name = "total_value", nullable = false)
    private float totalValue;

    @Column(name = "date", nullable = false)
    private float date;



}
