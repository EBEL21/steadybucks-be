package app.ebel.steadybucks.entity;

import app.ebel.steadybucks.entity.eid.StockChartId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "stock_chart")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockChart {

    @EmbeddedId
    private StockChartId id;

    @ManyToOne
    @MapsId("stockCode")
    @JoinColumn(name = "stock_code", referencedColumnName = "code")
    private Stock stock;

    @Column(name = "open_price", nullable = false)
    private BigDecimal openPrice;
    @Column(name = "high_price", nullable = false)
    private BigDecimal highPrice;
    @Column(name = "low_price", nullable = false)
    private BigDecimal lowPrice;
    @Column(name = "close_price", nullable = false)
    private BigDecimal closePrice;
    @Column(name = "volume", nullable = false)
    private BigDecimal volume;
}

