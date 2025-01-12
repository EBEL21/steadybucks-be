package app.ebel.steadybucks.dto.base;

import app.ebel.steadybucks.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailDto {
    String stockCode;
    String stockName;
    TransactionType type;
    BigDecimal pricePerUnit;
    Integer quantity;
    LocalDateTime time;

    // 매도 트랜잭션의 경우 수익률 추가
    BigDecimal profitAmount;
    BigDecimal profitRate;
    BigDecimal tax;
}
