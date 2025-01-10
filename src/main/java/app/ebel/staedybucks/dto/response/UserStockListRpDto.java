package app.ebel.staedybucks.dto.response;

import app.ebel.staedybucks.dto.base.UserStockDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserStockListRpDto {

    Long userId;
    List<UserStockDto> stockList;
}
