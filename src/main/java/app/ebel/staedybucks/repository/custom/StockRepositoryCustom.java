package app.ebel.staedybucks.repository.custom;

import app.ebel.staedybucks.dto.base.StockChartDto;

public interface StockRepositoryCustom {
    StockChartDto getLatestStockChart(String stockCode);
}
