package app.ebel.steadybucks.repository.base;


import app.ebel.steadybucks.entity.User;
import app.ebel.steadybucks.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByNickname(String loginId);

}
