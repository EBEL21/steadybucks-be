package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.dto.base.StockDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StockListRpDto {
    List<StockDto> stockList;
    int numOfStock;
}
