package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class DashBoardController {

    private CategoryService categoryService;
    private AccountService accountService;
    private UserManagerService userManagerService;

    public DashBoardController(CategoryService categoryService, AccountService accountService, UserManagerService userManagerService) {
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.userManagerService = userManagerService;
    }

    @ModelAttribute("accountBalance")
    public String accountBalance(Authentication currentUser){
        User user = userManagerService.findByUsername(currentUser.getName());
        return user.getAccount().getBalance();
    }

    @ModelAttribute("categories")
    public List<Category> categories(Authentication currentUser){
        return loadCategoriesToView(currentUser);
    }

    @GetMapping("/dashboard")
    public String showDashBoard(Authentication currentUser, Model model) {
        return "dashboard";
    }

    private List<Category> loadCategoriesToView(Authentication currentUser){
        User user = userManagerService.findByUsername(currentUser.getName());
        List<String> categoryNames = categoryService.findCategoryNames(user.getId());
        List<Category> categories = categoryService.findCategories(user.getId());
        List<Category> groupByCategoryName = categoryListGroupByCategoryName(categoryNames, categories);
        return groupByCategoryName;
    }

    private List<Category> categoryListGroupByCategoryName(List<String> categoryNames, List<Category> categories){
        List<Category> resultList = new ArrayList<>();
        for (int i = 0; i < categoryNames.size(); i++) {
            List<Category> cat = new ArrayList<>();
            for (Category category : categories) {
                if (categoryNames.get(i).equals(category.getCategoryName())) {
                    cat.add(category);
                }
            }
            Category category = new Category();
            category.setCategoryName(categoryNames.get(i));
            category.setActualValue(sumCategoryValue(cat));
            resultList.add(category);
        }
        return resultList;
    }

    private String sumCategoryValue(List<Category> categories){
        Double sum = 0.0;
        for(Category c : categories){
            sum += Double.parseDouble(c.getActualValue());
        }
        return sum.toString();
    }
}
