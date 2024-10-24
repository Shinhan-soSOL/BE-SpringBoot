package sol.shinhansecuirty.sosolbe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sol.shinhansecuirty.sosolbe.Entity.Account;
import sol.shinhansecuirty.sosolbe.Entity.SmallChange;

public interface SmallChangeRepository extends JpaRepository<SmallChange, Integer> {
    SmallChange findByAccount(Account account);
}
