package pl.coderslab.FinanceManager.web.app;

import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.convarter.CategoryConverter;
import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Account;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.ScheduleTaskService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/app")
public class AddExpenseController {

    private final UserManagerService userManagerService;
    private final CategoryService categoryService;
    private final AccountService accountService;

    public AddExpenseController(UserManagerService userManagerService, CategoryService categoryService, AccountService accountService) {
        this.userManagerService = userManagerService;
        this.categoryService = categoryService;
        this.accountService = accountService;
    }

    @GetMapping("/addExpense")
    public String prepareAddExpense(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "addExpense";
    }

    @PostMapping("/addExpense")
    public String processAddExpense(@Valid CategoryDto categoryDto, BindingResult bindingResult, Authentication currentUser) {
        if (bindingResult.hasErrors()) {
            return "addExpense";
        }
        CategoryConverter categoryConverter = new CategoryConverter();
        User user = userManagerService.findByUsername(currentUser.getName());
        Category category = categoryConverter.convert(categoryDto);
        Account account = user.getAccount();
        accountService.setBalance(account.getId(), account.getBalance() - category.getActualValue());
        category.setAccount(account);
        category.setScheduled(false);
        categoryService.addExpense(category);
        return "redirect:dashboard";
    }

}
