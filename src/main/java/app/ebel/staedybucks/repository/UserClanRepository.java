package app.ebel.staedybucks.repository;

import app.ebel.staedybucks.dto.request.UserRegisterClanRqDto;
import app.ebel.staedybucks.entity.UserClan;
import app.ebel.staedybucks.repository.custom.UserClanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClanRepository extends JpaRepository<UserClan, Long>, UserClanRepositoryCustom {

}
