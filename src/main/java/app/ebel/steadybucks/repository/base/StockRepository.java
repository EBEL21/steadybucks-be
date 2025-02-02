package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.Stock;
import app.ebel.steadybucks.exception.community.ResourceNotFoundException;
import app.ebel.steadybucks.repository.custom.StockRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryCustom {

    Optional<Stock> findByCode(String code);

    default Stock findByCodeOrThrow(String code) {
        return findByCode(code)
                .orElseThrow(()->new ResourceNotFoundException("Stock@" + code));
    }
}
