package app.ebel.steadybucks.entity.eid;

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
