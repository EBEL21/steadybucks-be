package app.ebel.staedybucks.entity.eid;

import app.ebel.staedybucks.entity.Stock;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class StockChartId implements Serializable {

    private String stockCode;
    private LocalDateTime datetime;
}
