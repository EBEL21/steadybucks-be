package app.ebel.staedybucks.repository;

import app.ebel.staedybucks.entity.UserStock;
import app.ebel.staedybucks.entity.eid.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStockRepository extends JpaRepository<UserStock, UserStockId> {

    List<UserStock> findById_UserId(Long userId);
    List<UserStock> findById_StockCode(String stockCode);
}
