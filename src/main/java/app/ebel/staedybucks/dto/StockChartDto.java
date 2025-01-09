package app.ebel.staedybucks.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockChartDto {

    String stockCode;
    String stockName;
    BigDecimal openPrice;
    BigDecimal highPrice;
    BigDecimal lowPrice;
    BigDecimal closePrice;
    BigDecimal volume;
    LocalDateTime datetime;
}
