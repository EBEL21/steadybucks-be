package app.ebel.steadybucks.repository.custom;

import app.ebel.steadybucks.dto.base.StockChartDto;

public interface StockRepositoryCustom {
    StockChartDto getLatestStockChart(String stockCode);
}
