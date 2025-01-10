package app.ebel.staedybucks.repository.base;


import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {


}
