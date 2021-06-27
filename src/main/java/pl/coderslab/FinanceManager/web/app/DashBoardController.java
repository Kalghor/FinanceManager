package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.FinanceManager.domain.dto.AccountDto;
import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import javax.validation.Valid;
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

    @GetMapping("/changeBalance")
    public String prepareChangeBalance(Model model) {
        model.addAttribute("account", new AccountDto());
        return "changeBalance";
    }

    @PostMapping("/changeBalance")
    public String processChangeBalance(@Valid AccountDto accountDto, BindingResult bindingResult, Authentication currentUser) {
        if (bindingResult.hasErrors()) {
            return "changeBalance";
        }
        User user = userManagerService.findByUsername(currentUser.getName());
        if (accountDto.getAmountToAdd().equals("")) {
            accountService.setBalance(user.getAccount().getId(), Long.parseLong(accountDto.getBalance()) * 100L);
        } else {
            Long num1 = user.getAccount().getBalance();
            Long num2 = Long.parseLong(accountDto.getAmountToAdd()) * 100L;
            Long result = num1 + num2;
            accountService.setBalance(user.getAccount().getId(), result);
        }
        return "redirect:dashboard";
    }

    @GetMapping("/addExpense")
    public String prepareAddExpense(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "addExpense";
    }

    @PostMapping("/addExpense")
    public String processAddExpense(@Valid CategoryDto categoryDto, BindingResult bindingResult, Authentication currentUser) {
        if (bindingResult.hasErrors()) {
            return "addExpense";
        }
        User user = userManagerService.findByUsername(currentUser.getName());
        Category category = new Category(null, categoryDto.getCategoryName(), categoryDto.getDescription(), categoryDto.getActualValue(), LocalDate.now(), user.getAccount());
        categoryService.addExpense(category);
        return "redirect:dashboard";
    }

    @GetMapping("/showDetails/{categoryName}")
    public String showDetails(@PathVariable String categoryName, Authentication currentUser, Model model) {
        User user = userManagerService.findByUsername(currentUser.getName());
        model.addAttribute("rows", categoryService.findCategoryByName(categoryName, user.getAccount().getId()));
        return "showDetails";
    }

    @GetMapping("/delete/{id:\\d+}")
    public String deleteOneEntry(@PathVariable Long id) {
        categoryService.deleteOneEntry(id);
        return "redirect:dashboard";
    }

    @GetMapping("/deleteAll/{categoryName}")
    public String deleteAllFromCategory(@PathVariable String categoryName, Authentication currentUser) {
        User user = userManagerService.findByUsername(currentUser.getName());
        categoryService.deleteAllFromCategory(categoryName, user.getAccount().getId());
        return "dashboard";
    }

    @GetMapping("/edit/{id:\\d+}")
    public String prepareEditExpense(Model model, @PathVariable Long id) {
        Category categoryById = categoryService.findCategoryById(id);
        CategoryDto categoryDto = new CategoryDto(categoryById.getId(), categoryById.getCategoryName(), categoryById.getDescription(), categoryById.getActualValue(), categoryById.getLocalDate());
        model.addAttribute("categoryDto", categoryDto);
        return "edit";
    }

    @PostMapping("/edit")
    public String processEditExpense(@Valid CategoryDto categoryDto, BindingResult bindingResult, Authentication currentUser) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        User user = userManagerService.findByUsername(currentUser.getName());
        Category category = new Category(categoryDto.getId(), categoryDto.getCategoryName(), categoryDto.getDescription(), categoryDto.getActualValue(), LocalDate.now(), user.getAccount());
        categoryService.edit(category);
        return "redirect:dashboard";
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

    private String sumCategoryValue(List<Category> categories) {
        double sum = 0.0;
        for (Category c : categories) {
            sum += Double.parseDouble(c.getActualValue());
        }
        return Double.toString(sum);
    }
}
