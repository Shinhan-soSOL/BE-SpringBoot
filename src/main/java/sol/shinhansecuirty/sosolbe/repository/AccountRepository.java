package sol.shinhansecuirty.sosolbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sol.shinhansecuirty.sosolbe.Entity.Account;
import sol.shinhansecuirty.sosolbe.Entity.User;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountNumAndType(String accountNum, String type);
    Account findByUserAndType(User user, String type);
}
