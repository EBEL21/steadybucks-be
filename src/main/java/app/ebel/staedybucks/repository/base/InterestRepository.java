package app.ebel.staedybucks.repository.base;

import app.ebel.staedybucks.entity.Interest;
import app.ebel.staedybucks.repository.custom.InterestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long>, InterestRepositoryCustom {
}
