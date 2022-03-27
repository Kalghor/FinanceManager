package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.FinanceManager.domain.convarter.CategoryConverter;
import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Account;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class ShowDetailsController {

    private final CategoryService categoryService;
    private final AccountService accountService;
    private final UserManagerService userManagerService;


    public ShowDetailsController(CategoryService categoryService, AccountService accountService, UserManagerService userManagerService) {
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.userManagerService = userManagerService;
    }



    @GetMapping("/showDetails/{categoryName}")
    public String showDetails(@PathVariable String categoryName, Authentication currentUser, Model model) {
        User user = userManagerService.findByUsername(currentUser.getName());
        List<CategoryDto> rows = new ArrayList<>();
        List<Category> categories = categoryService.findCategoryByName(categoryName, user.getAccount().getId());
        CategoryConverter categoryConverter = new CategoryConverter();
        for(Category c : categories){
            rows.add(categoryConverter.convertToDto(c));
        }
        model.addAttribute("rows", rows);
        model.addAttribute("name", categoryName);
        return "showDetails";
    }

    @PostMapping("/delete")
    public String deleteOneEntry(@RequestParam String id, @RequestParam String categoryName, Authentication currentUser, @RequestParam String oldValue) {
        categoryService.deleteOneEntry(Long.valueOf(id));
        User user = userManagerService.findByUsername(currentUser.getName());
        List<Category> categoryByName = categoryService.findCategoryByName(categoryName, user.getAccount().getId());
        Account account = user.getAccount();
        Double doubleOldValue = Double.parseDouble(oldValue) * 100d;
        accountService.setBalance(account.getId(), account.getBalance() + doubleOldValue.longValue());
        if(!categoryByName.isEmpty()){
            return "redirect:showDetails/" + categoryName;
        }
        return "redirect:dashboard";
    }

    @PostMapping("/deleteScheduled")
    public String deleteOneScheduledEntry(@RequestParam String id, @RequestParam String categoryName, Authentication currentUser, @RequestParam String oldValue) {
        categoryService.deleteOneEntry(Long.valueOf(id));
        return "redirect:scheduledExpenses";
    }

    @PostMapping("/deleteAll")
    public String deleteAllFromCategory(@RequestParam String categoryName, Authentication currentUser, @RequestParam String checkAddingToAccountBalance) {
        User user = userManagerService.findByUsername(currentUser.getName());
        Long valueDeletedCategory = totalCategoryValue(currentUser, categoryName);
        if(checkAddingToAccountBalance.equals("true")){
            Account account = user.getAccount();
            accountService.setBalance(account.getId(), account.getBalance() + valueDeletedCategory);
        }
        categoryService.deleteAllFromCategory(categoryName, user.getAccount().getId());

        return "redirect:dashboard";
    }

    public Long totalCategoryValue(Authentication currentUser, String categoryName){
        User user = userManagerService.findByUsername(currentUser.getName());
        List<Category> categories = categoryService.findCategoryByName(categoryName,user.getAccount().getId());
        Long sum = 0L;
        if(categories.isEmpty()){
            return sum;
        }
        for(Category c : categories){
            sum += c.getActualValue();
        }
        return sum;
    }
}
