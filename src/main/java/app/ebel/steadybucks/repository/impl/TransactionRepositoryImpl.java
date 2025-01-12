package app.ebel.steadybucks.repository.impl;

import app.ebel.steadybucks.dto.base.CumulativeProfitDto;
import app.ebel.steadybucks.dto.base.CumulativeStockProfitDto;
import app.ebel.steadybucks.dto.base.TransactionDetailDto;
import app.ebel.steadybucks.repository.custom.TransactionRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.ebel.steadybucks.entity.QProfit.profit;
import static app.ebel.steadybucks.entity.QStock.stock;
import static app.ebel.steadybucks.entity.QTransaction.transaction;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public CumulativeProfitDto getTotalCumulativeProfit(Long userId) {

        CumulativeProfitDto dto = queryFactory.select(
                        Projections.constructor(
                                CumulativeProfitDto.class,
                                profit.profitAmount.sum().as("totalProfitAmount"),
                                profit.profitRate.avg().as("totalProfitRate"),
                                profit.tax.sum().as("totalTax")
                        )
                ).from(transaction)
                .join(transaction.profit, profit)
                .where(transaction.user.id.eq(userId))
                .fetchFirst();
        return dto;
    }

    @Override
    public List<TransactionDetailDto> getUserTransactions(Long userId) {

        List<TransactionDetailDto> dtos = queryFactory.select(
                        Projections.fields(
                                TransactionDetailDto.class,
                                stock.code.as("stockCode"),
                                stock.name.as("stockName"),
                                transaction.type,
                                transaction.pricePerUnit,
                                transaction.quantity,
                                transaction.time,
                                profit.profitAmount,
                                profit.profitRate,
                                profit.tax
                        )
                ).from(transaction)
                .leftJoin(transaction.profit, profit)
                .join(transaction.stock, stock)
                .where(transaction.user.id.eq(userId))
                .fetch();
        return dtos;
    }

    @Override
    public List<CumulativeStockProfitDto> getStockCumulativeProfit(Long userId) {
        List<CumulativeStockProfitDto> dtos = queryFactory.select(
                        Projections.constructor(
                                CumulativeStockProfitDto.class,
                                stock.code.as("stockCode"),
                                stock.name.as("stockName"),
                                profit.profitAmount.sum().as("totalProfitAmount"),
                                profit.profitRate.avg().as("totalProfitRate"),
                                profit.tax.sum().as("totalTax")
                        )
                ).from(transaction)
                .leftJoin(transaction.profit, profit)
                .join(transaction.stock, stock)
                .where(transaction.user.id.eq(userId))
                .groupBy(stock.code, stock.name)
                .fetch();
        return dtos;
    }
}
