package app.ebel.staedybucks.dto.base;

import app.ebel.staedybucks.enums.TradingType;
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
public class UserInterestSingleDto {

    private Long creatorId;
    private String creator;
    private String clan;
    private StockDto stockInfo;
    private LocalDate capturedAt;
    private LocalDate closedAt;
    private BigDecimal capturedPrice;
    private BigDecimal targetPrice;
    private TradingType tradingType;

    // 팔로우한 관심종목인 경우
    private InterestFollowDto followInfo;
}
