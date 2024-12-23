package app.ebel.staedybucks.entity;

import app.ebel.staedybucks.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "quantity", nullable = false)
    private float quantity;

    @Column(name = "price_per_unit", nullable = false)
    private float pricePerUnit;

    @Column(name = "total_value", nullable = false)
    private float totalValue;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

}
