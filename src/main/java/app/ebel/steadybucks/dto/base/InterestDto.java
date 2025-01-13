package app.ebel.steadybucks.dto.base;

import app.ebel.steadybucks.enums.TradingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterestDto {

    // 등록자 정보
    private Long creatorId;
    private String creatorName;
    private Long clanId;
    private String clanName;

    // 종목 정보
    private StockDto stock;

    // 기간, 가격 정보
    private LocalDate capturedAt;
    private LocalDate closedAt;
    private BigDecimal capturedPrice;
    private BigDecimal targetPrice;
    private TradingType tradingType;
}
