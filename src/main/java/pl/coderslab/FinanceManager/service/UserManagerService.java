package pl.coderslab.FinanceManager.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.FinanceManager.domain.model.Account;
import pl.coderslab.FinanceManager.domain.model.User;
//import pl.coderslab.FinanceManager.domain.repository.AccountRepository;
import pl.coderslab.FinanceManager.domain.repository.AccountRepository;
import pl.coderslab.FinanceManager.domain.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class UserManagerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public UserManagerService(UserRepository userRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    public void registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        Account account = new Account(null, 0L, user, null);
        accountRepository.save(account);
        user.setAccount(account);
        userRepository.save(user);
    }

    public User findByUsername(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
