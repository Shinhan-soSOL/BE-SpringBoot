package sol.shinhansecuirty.sosolbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sol.shinhansecuirty.sosolbe.Entity.Target;
import sol.shinhansecuirty.sosolbe.Entity.User;

public interface TargetRepository extends JpaRepository<Target, Integer> {
    Target findByUser(User user);
}
