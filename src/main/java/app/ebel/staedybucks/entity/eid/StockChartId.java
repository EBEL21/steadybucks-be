package app.ebel.staedybucks.entity.eid;

import app.ebel.staedybucks.entity.Stock;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class StockChartId implements Serializable {

    @NotBlank
    private String stockCode;
    @NotBlank
    private LocalDateTime datetime;
}
