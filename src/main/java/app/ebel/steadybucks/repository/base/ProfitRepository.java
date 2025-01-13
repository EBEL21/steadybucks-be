package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.Profit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfitRepository extends JpaRepository<Profit, Long> {
}
