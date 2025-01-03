package app.ebel.staedybucks.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockDto {

    String code;
    String name;
    String type;
    String market;
    Long price;
}
