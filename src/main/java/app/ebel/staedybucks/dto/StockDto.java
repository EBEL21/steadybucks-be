package app.ebel.staedybucks.dto;

import app.ebel.staedybucks.entity.Stock;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    String code;
    String name;
    String type;
    String market;
//    BigDecimal price;

    public StockDto(Stock stock) {
        this.code = stock.getCode();
        this.name = stock.getName();
        this.type = stock.getType();
        this.market = stock.getMarket();
//        this.price = new BigDecimal("0.0");
    }
}
