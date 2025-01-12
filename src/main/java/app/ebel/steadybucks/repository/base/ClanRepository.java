package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.Clan;
import app.ebel.steadybucks.repository.custom.ClanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanRepository extends JpaRepository<Clan, Long>, ClanRepositoryCustom {
    boolean existsClanByName(String name);
}
