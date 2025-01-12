package app.ebel.steadybucks.repository.base;


import app.ebel.steadybucks.entity.User;
import app.ebel.steadybucks.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends BaseRepository<User, Long>, UserRepositoryCustom {
}
