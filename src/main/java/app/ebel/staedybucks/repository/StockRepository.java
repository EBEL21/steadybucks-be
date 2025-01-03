package app.ebel.staedybucks.repository;

import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.repository.custom.StockRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryCustom {
}
