package app.ebel.staedybucks.repository.base;

import app.ebel.staedybucks.entity.UserClan;
import app.ebel.staedybucks.repository.custom.UserClanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClanRepository extends JpaRepository<UserClan, Long>, UserClanRepositoryCustom {

}
