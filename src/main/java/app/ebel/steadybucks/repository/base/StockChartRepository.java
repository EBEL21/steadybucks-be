package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.StockChart;
import app.ebel.steadybucks.entity.eid.StockChartId;
import app.ebel.steadybucks.repository.custom.StockChartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockChartRepository extends JpaRepository<StockChart, StockChartId>, StockChartRepositoryCustom {

}
