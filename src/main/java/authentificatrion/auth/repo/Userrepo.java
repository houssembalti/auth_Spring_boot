package authentificatrion.auth.repo;

import authentificatrion.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepo extends JpaRepository<User,Long> {

  User findByUsername(String username);
}
