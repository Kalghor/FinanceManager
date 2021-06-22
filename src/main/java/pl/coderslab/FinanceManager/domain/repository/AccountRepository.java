package pl.coderslab.FinanceManager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.FinanceManager.domain.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
