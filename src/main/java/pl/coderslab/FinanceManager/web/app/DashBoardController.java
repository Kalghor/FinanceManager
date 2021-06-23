package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.domain.repository.UserRepository;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class DashBoardController {

    private CategoryService categoryService;
    private AccountService accountService;
    private final UserRepository userRepository;

    public DashBoardController(CategoryService categoryService, AccountService accountService, UserRepository userRepository) {
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("categories")
    public List<Category> categories(Authentication currentUser){
        Optional<User> optionalUser = userRepository.findByUsername(currentUser.getName());
        User user = optionalUser.get();
        return categoryService.findCategories(user.getId());
    }

    @GetMapping("/dashboard")
    public String showDashBoard(){
        return "dashboard";
    }

}
