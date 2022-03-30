package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.UserManagerService;

@Controller
@RequestMapping("/app")
public class ScheduledExpensesLoaderController {

    private final ScheduledExpensesLoader scheduledExpensesLoader;
    private final UserManagerService userManagerService;

    public ScheduledExpensesLoaderController(ScheduledExpensesLoader scheduledExpensesLoader, UserManagerService userManagerService) {
        this.scheduledExpensesLoader = scheduledExpensesLoader;
        this.userManagerService = userManagerService;
    }

    @GetMapping("/loadScheduledExpenses")
    public String processAddScheduledExpense(Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        scheduledExpensesLoader.loadScheduledExpenses(user);
        return "redirect:dashboard";
    }
}
