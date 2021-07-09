package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import java.util.List;

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
        model.addAttribute("name", categoryName);
        return "showDetails";
    }

    @PostMapping("/delete")
    public String deleteOneEntry(@RequestParam String id, @RequestParam String categoryName, Authentication currentUser) {
        categoryService.deleteOneEntry(Long.valueOf(id));
        User user = userManagerService.findByUsername(currentUser.getName());
        List<Category> categoryByName = categoryService.findCategoryByName(categoryName, user.getAccount().getId());
        if(!categoryByName.isEmpty()){
            return "redirect:showDetails/" + categoryName;
        }
        return "redirect:dashboard";
    }

    @PostMapping("/deleteAll")
    public String deleteAllFromCategory(@RequestParam String categoryName, Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        categoryService.deleteAllFromCategory(categoryName, user.getAccount().getId());
        return "redirect:dashboard";
    }
}
