package app.ebel.staedybucks.dto;

import app.ebel.staedybucks.entity.UserStock;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserStockDto {
    String stockCode;
    String stockName;
    private BigDecimal totalValue;
    private BigDecimal averagePrice;
    private Integer quantity;

    public UserStockDto(UserStock us) {
        this.stockCode = us.getStock().getCode();
        this.stockName = us.getStock().getName();
        this.totalValue = us.getTotalValue();
        this.averagePrice = us.getPricePerUnit();
        this.quantity = us.getQuantity();
    }
}
