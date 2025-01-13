package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.Transaction;
import app.ebel.steadybucks.entity.eid.UserStockId;
import app.ebel.steadybucks.repository.custom.TransactionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UserStockId>, TransactionRepositoryCustom {
}
