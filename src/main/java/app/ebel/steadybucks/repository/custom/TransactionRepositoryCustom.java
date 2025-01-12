package app.ebel.steadybucks.repository.custom;

import app.ebel.steadybucks.dto.base.CumulativeProfitDto;
import app.ebel.steadybucks.dto.base.CumulativeStockProfitDto;
import app.ebel.steadybucks.dto.base.TransactionDetailDto;

import java.util.List;

public interface TransactionRepositoryCustom {
    CumulativeProfitDto getTotalCumulativeProfit(Long userId);
    List<TransactionDetailDto> getUserTransactions(Long userId);
    List<CumulativeStockProfitDto> getStockCumulativeProfit(Long userId);
}
