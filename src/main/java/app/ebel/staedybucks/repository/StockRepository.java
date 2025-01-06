package app.ebel.staedybucks.repository;

import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.repository.custom.StockRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryCustom {

    Optional<Stock> findByCode(String code);
}
