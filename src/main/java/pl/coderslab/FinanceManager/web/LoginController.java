package pl.coderslab.FinanceManager.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.ScheduleTaskService;
import pl.coderslab.FinanceManager.web.app.ScheduledExpensesLoader;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private ScheduledExpensesLoader scheduledExpensesLoader;
    private ScheduleTaskService scheduleTaskService;
    private static int counter = 0;



    public LoginController(ScheduledExpensesLoader scheduledExpensesLoader, ScheduleTaskService scheduleTaskService) {
        this.scheduledExpensesLoader = scheduledExpensesLoader;
        this.scheduleTaskService = scheduleTaskService;
    }

    @GetMapping
    public String prepareLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String processLogin(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
//        if(counter == 0){
////            User user = userManagerService.findByUsername(currentUser.getName());
//            scheduledExpensesLoader.loadScheduledExpenses(user);
//            counter++;
//        }
        return "redirect:dashboard";
    }

}
