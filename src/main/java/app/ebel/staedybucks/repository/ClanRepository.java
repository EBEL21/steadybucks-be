package app.ebel.staedybucks.repository;

import app.ebel.staedybucks.entity.Clan;
import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.repository.custom.ClanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClanRepository extends JpaRepository<Clan, Long>, ClanRepositoryCustom {
    boolean existsClanByName(String name);
}
