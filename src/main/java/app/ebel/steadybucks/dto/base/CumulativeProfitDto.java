package app.ebel.steadybucks.dto.base;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CumulativeProfitDto {
    BigDecimal totalProfitAmount;
    BigDecimal totalProfitRate;
    BigDecimal totalTax;

    public void setTotalProfitRate(Double totalProfitRate) {
        this.totalProfitRate = totalProfitRate != null ? BigDecimal.valueOf(totalProfitRate) : BigDecimal.ZERO;
    }

    public void setTotalProfitAmount(BigDecimal totalProfitAmount) {
        this.totalProfitAmount = totalProfitAmount;
    }

    public CumulativeProfitDto(BigDecimal totalProfitAmount, Double totalProfitRate, BigDecimal totalTax) {
        this.totalProfitAmount = totalProfitAmount;
        this.totalProfitRate = totalProfitRate != null ? BigDecimal.valueOf(totalProfitRate) : BigDecimal.ZERO;
        this.totalTax = totalTax;
    }
}
