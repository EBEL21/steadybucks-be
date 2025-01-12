package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.Interest;
import app.ebel.steadybucks.repository.custom.InterestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long>, InterestRepositoryCustom {
}
