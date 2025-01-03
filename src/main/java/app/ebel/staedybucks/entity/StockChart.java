package app.ebel.staedybucks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_chart")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_code", nullable = false, referencedColumnName = "code")
    private Stock stock;
    @Column(name = "open_price", nullable = false)
    private float openPrice;
    @Column(name = "high_price", nullable = false)
    private float highPrice;
    @Column(name = "low_price", nullable = false)
    private float lowPrice;
    @Column(name = "close_price", nullable = false)
    private float closePrice;
    @Column(name = "volume", nullable = false)
    private float volume;
    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

}
