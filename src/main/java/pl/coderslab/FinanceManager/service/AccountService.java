package pl.coderslab.FinanceManager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.repository.AccountRepository;
//import pl.coderslab.FinanceManager.domain.repository.AccountRepository;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long getBalance(Long accountId){
        return accountRepository.findByUsername(accountId).getBalance();
    }

    public void setBalance(Long accountId, Long balance){
        accountRepository.findByUsername(accountId).setBalance(balance);
    }
}
