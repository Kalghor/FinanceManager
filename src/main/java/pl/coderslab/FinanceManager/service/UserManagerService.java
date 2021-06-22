package pl.coderslab.FinanceManager.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.domain.repository.UserRepository;

@Service
@Transactional
public class UserManagerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagerService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
