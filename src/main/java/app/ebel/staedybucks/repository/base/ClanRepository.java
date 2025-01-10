package app.ebel.staedybucks.repository.base;

import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.repository.custom.ClanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanRepository extends JpaRepository<Clan, Long>, ClanRepositoryCustom {
    boolean existsClanByName(String name);
}
