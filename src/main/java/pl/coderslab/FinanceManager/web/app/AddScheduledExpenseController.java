package pl.coderslab.FinanceManager.web.app;

import org.springframework.scheduling.annotation.Scheduled;
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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/app")
public class AddScheduledExpenseController {

    private final UserManagerService userManagerService;
    private final CategoryService categoryService;
    private final AccountService accountService;
    private final ScheduleTaskService scheduleTaskService;

    public AddScheduledExpenseController(UserManagerService userManagerService, CategoryService categoryService, AccountService accountService, ScheduleTaskService scheduleTaskService) {
        this.userManagerService = userManagerService;
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.scheduleTaskService = scheduleTaskService;
    }

    @GetMapping("/addScheduledExpense")
    public String prepareAddExpense(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "addScheduledExpense";
    }

    @PostMapping("/addScheduledExpense")
    public String processAddScheduledExpense(@Valid CategoryDto categoryDto, BindingResult bindingResult, Authentication currentUser) {
        if (bindingResult.hasErrors()) {
            return "addScheduledExpense";
        }
        CategoryConverter categoryConverter = new CategoryConverter();
        User user = userManagerService.findByUsername(currentUser.getName());
        Category category = categoryConverter.convert(categoryDto);
        Account account = user.getAccount();
        category.setAccount(account);
        category.setScheduled(true);
        Runnable task = () -> addSchedulingExpense(user, category);
        scheduleTaskService.addTaskToScheduler(235345, task, category.getSchedulingDate(), category.isMonthly(), category.getSchedulingStopDate());
        categoryService.addExpense(category);
        return "redirect:scheduledExpenses";
    }

    public void addSchedulingExpense(User user, Category category) {
        Account account = user.getAccount();
        categoryService.addExpense(category);
        accountService.setBalance(account.getId(), account.getBalance() - category.getActualValue());
    }

    @GetMapping("/demoScheduledExpense")
    public String processAddDemoScheduledExpense(Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        Account account = user.getAccount();
        Category category = new Category();
        category.setScheduled(true);

        Runnable task = () -> addDemoSchedulingExpense();
        scheduleTaskService.addTaskToScheduler(235345, task);
        return "redirect:dashboard";
    }

    public String addDemoSchedulingExpense() {
        User user = userManagerService.findByUsername("test@wp.pl");
        Account account = user.getAccount();
        Category category = new Category();
        category.setActualValue(500L);
        category.setMonthly(true);
        category.setCategoryName("Demo scheduling");
        category.setAccount(account);
        category.setDescription("Demo test");
        category.setLocalDate(LocalDate.now());
        category.setScheduled(false);
        categoryService.addExpense(category);
        accountService.setBalance(account.getId(), account.getBalance() - 500L);
        return "redirect:dashboard";
    }
}
