package app.ebel.staedybucks.dto.request;

import app.ebel.staedybucks.entity.Interest;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AddInterestRqDto {

    private Long creatorId;
    private Long clanId;
    private LocalDate capturedAt;
    private LocalDate closedAt;
    private String stockCode;
    private BigDecimal capturedPrice;
    private BigDecimal targetPrice;
    private String tradingType;

}
