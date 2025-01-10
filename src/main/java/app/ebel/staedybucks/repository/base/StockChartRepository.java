package app.ebel.staedybucks.repository.base;

import app.ebel.staedybucks.entity.StockChart;
import app.ebel.staedybucks.entity.eid.StockChartId;
import app.ebel.staedybucks.repository.custom.StockChartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockChartRepository extends JpaRepository<StockChart, StockChartId>, StockChartRepositoryCustom {

}
