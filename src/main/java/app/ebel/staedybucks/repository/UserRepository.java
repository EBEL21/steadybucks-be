package app.ebel.staedybucks.repository;


import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {


}
