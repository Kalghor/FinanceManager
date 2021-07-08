package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.dto.AccountDto;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/app")
public class ChangeBalanceController {

    private final AccountService accountService;
    private final UserManagerService userManagerService;


    public ChangeBalanceController(AccountService accountService, UserManagerService userManagerService) {
        this.accountService = accountService;
        this.userManagerService = userManagerService;
    }

    @ModelAttribute("accountBalance")
    public String accountBalance(Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        Long partOfTheTotal = user.getAccount().getBalance() / 100;
        Long decimalPart = user.getAccount().getBalance() % 100;
        return partOfTheTotal + "," + decimalPart + " zł";
    }

    @GetMapping("/changeBalance")
    public String prepareChangeBalance(Model model) {
        model.addAttribute("account", new AccountDto());
        return "changeBalance";
    }

    @PostMapping("/changeBalance")
    public String processChangeBalance(@Valid AccountDto accountDto, BindingResult bindingResult, Authentication currentUser) {
        if (bindingResult.hasErrors()) {
            return "changeBalance";
        }
        User user = userManagerService.findByUsername(currentUser.getName());
        if (accountDto.getAmountToAdd().equals("")) {
            accountService.setBalance(user.getAccount().getId(), Long.parseLong(accountDto.getBalance()) * 100L);
        } else {
            Long num1 = user.getAccount().getBalance();
            Long num2 = Long.parseLong(accountDto.getAmountToAdd()) * 100L;
            Long result = num1 + num2;
            accountService.setBalance(user.getAccount().getId(), result);
        }
        return "redirect:dashboard";
    }

}
