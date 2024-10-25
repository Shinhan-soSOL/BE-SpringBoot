package sol.shinhansecuirty.sosolbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sol.shinhansecuirty.sosolbe.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

