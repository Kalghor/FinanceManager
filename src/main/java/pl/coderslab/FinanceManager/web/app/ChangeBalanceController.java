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
        model.addAttribute("accountDto", new AccountDto());
        return "changeBalance";
    }

    @PostMapping("/changeBalance")
    public String processChangeBalance(@Valid AccountDto accountDto, BindingResult bindingResult, Authentication currentUser, Model model) {
        if (bindingResult.hasErrors()) {
            return "changeBalance";
        }
        if(accountDto.getAmountToAdd().equals("") && accountDto.getBalance().equals("")){
            model.addAttribute("requiredParam", "Complete one of the values");
            return "changeBalance";
        }
        User user = userManagerService.findByUsername(currentUser.getName());
        if (accountDto.getAmountToAdd().equals("")) {
            Double amountToAdd = Double.parseDouble(accountDto.getBalance()) * 100d;
            accountService.setBalance(user.getAccount().getId(), amountToAdd.longValue());
        } else {
            Long num1 = user.getAccount().getBalance();
            Double num2 = Double.parseDouble(accountDto.getAmountToAdd()) * 100d;
            Long result = num1 + num2.longValue();
            accountService.setBalance(user.getAccount().getId(), result);
        }
        return "redirect:dashboard";
    }

}
