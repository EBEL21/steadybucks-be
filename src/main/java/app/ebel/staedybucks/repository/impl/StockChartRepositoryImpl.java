package app.ebel.staedybucks.repository.impl;

import app.ebel.staedybucks.dto.StockChartDto;
import app.ebel.staedybucks.repository.custom.StockChartRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static app.ebel.staedybucks.entity.QStock.stock;
import static app.ebel.staedybucks.entity.QStockChart.stockChart;

@Repository
@RequiredArgsConstructor
public class StockChartRepositoryImpl implements StockChartRepositoryCustom {
}
