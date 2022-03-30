package pl.coderslab.FinanceManager.web.app;

import org.springframework.stereotype.Component;
import pl.coderslab.FinanceManager.domain.model.Account;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.ScheduleTaskService;

import java.util.List;

@Component
public class ScheduledExpensesLoader {

    private CategoryService categoryService;
    private ScheduleTaskService scheduleTaskService;
    private AccountService accountService;

    ScheduledExpensesLoader(CategoryService categoryService, ScheduleTaskService scheduleTaskService, AccountService accountService) {
        this.categoryService = categoryService;
        this.scheduleTaskService = scheduleTaskService;
        this.accountService = accountService;
    }

    public void loadScheduledExpenses(User user){
        Account account = user.getAccount();
        List<Category> scheduledExpenses = categoryService.getScheduledExpenses(account.getId());
        if(!scheduledExpenses.isEmpty()){
            for (Category c : scheduledExpenses){
                Runnable task = () -> addSchedulingExpense(user, c);
                scheduleTaskService.addTaskToScheduler(c.getId().intValue(), task, c.getSchedulingDate(), c.isScheduled(), null);
            }
        }
        scheduleTaskService.showScheduledJobs("Loaded ids: ");
    }

    public void addSchedulingExpense(User user, Category category) {
        Account account = user.getAccount();
        categoryService.addExpense(category);
        accountService.setBalance(account.getId(), account.getBalance() - category.getActualValue());
    }

}
