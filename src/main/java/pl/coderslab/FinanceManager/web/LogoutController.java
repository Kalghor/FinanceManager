package pl.coderslab.FinanceManager.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.ScheduleTaskService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/removeDemo")
public class LogoutController {

    private final UserManagerService userManagerService;
    private final CategoryService categoryService;
    private final ScheduleTaskService scheduleTaskService;


    public LogoutController(UserManagerService userManagerService, CategoryService categoryService, ScheduleTaskService scheduleTaskService) {
        this.userManagerService = userManagerService;
        this.categoryService = categoryService;
        this.scheduleTaskService = scheduleTaskService;
    }


    @GetMapping
    public String processLogout(Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        List<Category> scheduledExpenses = categoryService.getScheduledExpenses(user.getAccount().getId());
        List<Category> demoScheduledExpenses = scheduledExpenses.stream()
                .filter(category -> category.getDescription().equals("Demo test"))
                .collect(Collectors.toList());
        if(!demoScheduledExpenses.isEmpty()){
            for(Category c : demoScheduledExpenses){
                categoryService.deleteOneEntry(c.getId());
                scheduleTaskService.removeTaskFromScheduler(Math.toIntExact(c.getId()));
            }
        }
        return "redirect:/logout";
    }


}
