package sol.shinhansecuirty.sosolbe.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sol.shinhansecuirty.sosolbe.Entity.History;
import sol.shinhansecuirty.sosolbe.Entity.User;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByUser(User user);
}
