package app.ebel.staedybucks.repository.custom;

import app.ebel.staedybucks.dto.base.CumulativeProfitDto;
import app.ebel.staedybucks.dto.base.CumulativeStockProfitDto;
import app.ebel.staedybucks.dto.base.TransactionDetailDto;
import app.ebel.staedybucks.dto.response.TradeSummaryRpDto;

import java.util.List;

public interface TransactionRepositoryCustom {
    CumulativeProfitDto getTotalCumulativeProfit(Long userId);
    List<TransactionDetailDto> getUserTransactions(Long userId);
    List<CumulativeStockProfitDto> getStockCumulativeProfit(Long userId);
}
