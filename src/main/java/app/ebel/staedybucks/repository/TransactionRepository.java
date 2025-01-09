package app.ebel.staedybucks.repository;

import app.ebel.staedybucks.entity.Transaction;
import app.ebel.staedybucks.entity.eid.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UserStockId> {
}
