package pl.coderslab.FinanceManager.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.FinanceManager.domain.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
