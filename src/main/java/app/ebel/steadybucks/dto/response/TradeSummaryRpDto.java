package app.ebel.steadybucks.dto.response;

import app.ebel.steadybucks.dto.base.CumulativeProfitDto;
import app.ebel.steadybucks.dto.base.CumulativeStockProfitDto;
import app.ebel.steadybucks.dto.base.TransactionDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeSummaryRpDto {

    //누적 수익
    CumulativeProfitDto total;
    // 전체 트랜잭션
    List<TransactionDetailDto> transactions;
    // 종목별 수익
    List<CumulativeStockProfitDto> stocks;
}
