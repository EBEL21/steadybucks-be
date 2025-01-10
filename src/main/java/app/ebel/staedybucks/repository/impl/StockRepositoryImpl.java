package app.ebel.staedybucks.repository.impl;

import app.ebel.staedybucks.dto.base.StockChartDto;
import app.ebel.staedybucks.repository.custom.StockRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static app.ebel.staedybucks.entity.QStock.stock;
import static app.ebel.staedybucks.entity.QStockChart.stockChart;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public StockChartDto getLatestStockChart(String stockCode) {

        queryFactory.select(
                        Projections.fields(
                                StockChartDto.class,
                                stock.code,
                                stock.name,
                                stockChart.openPrice,
                                stockChart.highPrice,
                                stockChart.lowPrice,
                                stockChart.closePrice,
                                stockChart.volume,
                                stockChart.id.datetime
                        )
                ).from(stock)
                .join(stockChart).on(stock.code.eq(stockChart.id.stockCode))
                .orderBy(stockChart.id.datetime.desc())
                .where(stock.code.eq(stockCode))
                .fetchFirst();
        return null;
    }
}
