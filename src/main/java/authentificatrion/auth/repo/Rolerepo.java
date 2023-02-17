package authentificatrion.auth.repo;

import authentificatrion.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rolerepo extends JpaRepository<Role,Long> {
  Role findByName(String name);
}
