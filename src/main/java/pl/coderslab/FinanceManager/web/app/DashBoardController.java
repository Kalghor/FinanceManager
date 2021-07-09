package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class DashBoardController {

    private final CategoryService categoryService;
    private final AccountService accountService;
    private final UserManagerService userManagerService;

    public DashBoardController(CategoryService categoryService, AccountService accountService, UserManagerService userManagerService) {
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.userManagerService = userManagerService;
    }

    @ModelAttribute("accountBalance")
    public String accountBalance(Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        Long partOfTheTotal = user.getAccount().getBalance() / 100;
        Long decimalPart = user.getAccount().getBalance() % 100;
        return partOfTheTotal + "," + decimalPart + " zł";
    }

    @ModelAttribute("categories")
    public List<Category> categories(Authentication currentUser) {
        return loadCategoriesToView(currentUser);
    }

    @GetMapping("/dashboard")
    public String showDashBoard() {
        return "dashboard";
    }


    private List<Category> loadCategoriesToView(Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        List<String> categoryNames = categoryService.findCategoryNames(user.getId());
        List<Category> categories = categoryService.findCategories(user.getId());
        return categoryListGroupByCategoryName(categoryNames, categories);
    }

    private List<Category> categoryListGroupByCategoryName(List<String> categoryNames, List<Category> categories) {
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

    private Long sumCategoryValue(List<Category> categories) {
        Double sum = 0.0;
        for (Category c : categories) {
            sum += c.getActualValue().doubleValue();
        }
        return sum.longValue();
    }
}
