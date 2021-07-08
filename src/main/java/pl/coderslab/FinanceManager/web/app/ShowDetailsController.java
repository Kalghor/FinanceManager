package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

@Controller
@RequestMapping("/app")
public class ShowDetailsController {

    private final CategoryService categoryService;

    public ShowDetailsController(CategoryService categoryService, UserManagerService userManagerService) {
        this.categoryService = categoryService;
        this.userManagerService = userManagerService;
    }

    private final UserManagerService userManagerService;



    @GetMapping("/showDetails/{categoryName}")
    public String showDetails(@PathVariable String categoryName, Authentication currentUser, Model model) {
        User user = userManagerService.findByUsername(currentUser.getName());
        model.addAttribute("rows", categoryService.findCategoryByName(categoryName, user.getAccount().getId()));
        return "showDetails";
    }

    @PostMapping("/delete")
    public String deleteOneEntry(@RequestParam String id) {
        categoryService.deleteOneEntry(Long.valueOf(id));
        return "redirect:dashboard";
    }

    @GetMapping("/deleteAll")
    public String deleteAllFromCategory(@RequestParam String categoryName, Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        categoryService.deleteAllFromCategory(categoryName, user.getAccount().getId());
        return "redirect:dashboard";
    }

}
