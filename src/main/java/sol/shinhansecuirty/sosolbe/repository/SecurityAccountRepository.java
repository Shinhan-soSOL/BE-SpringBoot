package sol.shinhansecuirty.sosolbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sol.shinhansecuirty.sosolbe.Entity.SecurityAccount;
import sol.shinhansecuirty.sosolbe.Entity.User;

import java.util.List;

public interface SecurityAccountRepository extends JpaRepository<SecurityAccount, Integer> {
    List<SecurityAccount> findByUser(User user);
}
