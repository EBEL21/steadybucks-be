package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.dto.base.UserStockDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserStockListRpDto {

    Long userId;
    List<UserStockDto> stockList;
}
