package app.ebel.steadybucks.dto.base;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CumulativeStockProfitDto {
    String stockCode;
    String stockName;
    BigDecimal totalProfitAmount;
    BigDecimal totalProfitRate;
    BigDecimal totalTax;

    public void setTotalProfitRate(Double totalProfitRate) {
        this.totalProfitRate = totalProfitRate != null ? BigDecimal.valueOf(totalProfitRate) : BigDecimal.ZERO;
    }

    public void setTotalProfitAmount(BigDecimal totalProfitAmount) {
        this.totalProfitAmount = totalProfitAmount;
    }

    public CumulativeStockProfitDto(String stockCode, String stockName, BigDecimal totalProfitAmount, Double totalProfitRate, BigDecimal totalTax) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.totalProfitAmount = totalProfitAmount != null ? totalProfitAmount : BigDecimal.ZERO;
        this.totalProfitRate = totalProfitRate != null ? BigDecimal.valueOf(totalProfitRate) : BigDecimal.ZERO;
        this.totalTax = totalTax;
    }
}
