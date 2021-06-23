package pl.coderslab.FinanceManager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.FinanceManager.domain.model.Account;
import pl.coderslab.FinanceManager.domain.model.Category;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.id = ?1")
    Account findByUsername(Long accountId);
}
