package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.FinanceManager.domain.convarter.CategoryConverter;
import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.ScheduleTaskService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class ScheduledDetailsController {

    private final CategoryService categoryService;
    private final AccountService accountService;
    private final UserManagerService userManagerService;
    private final ScheduleTaskService scheduleTaskService;

    public ScheduledDetailsController(CategoryService categoryService, AccountService accountService, UserManagerService userManagerService, ScheduleTaskService scheduleTaskService) {
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.userManagerService = userManagerService;
        this.scheduleTaskService = scheduleTaskService;
    }

    @GetMapping("/scheduledExpenses")
    public String showScheduledExpenses(Authentication currentUser, Model model) {
        User user = userManagerService.findByUsername(currentUser.getName());
        List<CategoryDto> rows = new ArrayList<>();
        List<Category> categories = categoryService.getScheduledExpenses(user.getAccount().getId());
        CategoryConverter categoryConverter = new CategoryConverter();
        for(Category c : categories){
            rows.add(categoryConverter.convertToDto(c));
        }
        model.addAttribute("rows", rows);
        return "scheduledExpenses";
    }

    @GetMapping("/deleteAllScheduled")
    public String deleteAllScheduled(Authentication currentUser){
        User user = userManagerService.findByUsername(currentUser.getName());
        List<Category> scheduledExpenses = categoryService.getScheduledExpenses(user.getAccount().getId());
        if(!scheduledExpenses.isEmpty()){
            scheduleTaskService.removeTasksFromScheduler(scheduledExpenses);
            categoryService.deleteAllScheduled(user.getAccount().getId());
        }
        return "redirect:dashboard";
    }

    @PostMapping("/deleteScheduled")
    public String deleteOneScheduledEntry(@RequestParam String id) {
        scheduleTaskService.removeTaskFromScheduler(Integer.parseInt(id));
        categoryService.deleteOneEntry(Long.valueOf(id));
        return "redirect:scheduledExpenses";
    }

}
