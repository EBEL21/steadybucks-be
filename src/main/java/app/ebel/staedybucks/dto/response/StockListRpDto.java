package app.ebel.staedybucks.dto.response;

import app.ebel.staedybucks.dto.StockDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StockListRpDto {
    List<StockDto> stockList;
    int numOfStock;
}
