package app.ebel.steadybucks.dto.base;

import app.ebel.steadybucks.entity.Stock;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    String code;
    String name;
    String type;
    String market;

    public StockDto(Stock stock) {
        this.code = stock.getCode();
        this.name = stock.getName();
        this.type = stock.getType();
        this.market = stock.getMarket();
    }
}
