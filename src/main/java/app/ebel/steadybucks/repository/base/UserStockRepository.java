package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.UserStock;
import app.ebel.steadybucks.entity.eid.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStockRepository extends BaseRepository<UserStock, UserStockId> {

    List<UserStock> findById_UserId(Long userId);
    List<UserStock> findById_StockCode(String stockCode);
}
