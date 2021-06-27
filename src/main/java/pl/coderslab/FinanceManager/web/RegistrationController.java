package pl.coderslab.FinanceManager.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.UserManagerService;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserManagerService userManagerService;

    public RegistrationController(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @GetMapping
    public String prepareRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String processRegistrationPage(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userManagerService.registerUser(user);
        return "login";
    }
}
