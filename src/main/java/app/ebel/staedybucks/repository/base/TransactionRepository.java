package app.ebel.staedybucks.repository.base;

import app.ebel.staedybucks.entity.Transaction;
import app.ebel.staedybucks.entity.eid.UserStockId;
import app.ebel.staedybucks.repository.custom.TransactionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UserStockId>, TransactionRepositoryCustom {
}
