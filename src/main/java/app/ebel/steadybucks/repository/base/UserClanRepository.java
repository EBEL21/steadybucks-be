package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.entity.UserClan;
import app.ebel.steadybucks.repository.custom.UserClanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClanRepository extends JpaRepository<UserClan, Long>, UserClanRepositoryCustom {

}
