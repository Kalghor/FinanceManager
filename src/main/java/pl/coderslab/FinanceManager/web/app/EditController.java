package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.FinanceManager.domain.convarter.CategoryConverter;
import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Account;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.AccountService;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/app")
public class EditController {

    private final CategoryService categoryService;
    private final UserManagerService userManagerService;
    private final AccountService accountService;

    public EditController(CategoryService categoryService, UserManagerService userManagerService, AccountService accountService) {
        this.categoryService = categoryService;
        this.userManagerService = userManagerService;
        this.accountService = accountService;
    }

    @GetMapping("/edit/{id:\\d+}")
    public String prepareEditExpense(Model model, @PathVariable Long id) {
        CategoryConverter categoryConverter = new CategoryConverter();
        CategoryDto categoryDto = categoryConverter.convertToDto(categoryService.findCategoryById(id));
        model.addAttribute("categoryDto", categoryDto);
        model.addAttribute("oldValue", categoryDto.getActualValue());
        return "edit";
    }

    @PostMapping("/edit")
    public String processEditExpense(@Valid CategoryDto categoryDto, BindingResult bindingResult, Authentication currentUser, @RequestParam String oldValue) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        CategoryConverter categoryConverter = new CategoryConverter();
        Category category = categoryService.findCategoryById(categoryDto.getId());
        Category convertedCategory = categoryConverter.convert(categoryDto, category);
        User user = userManagerService.findByUsername(currentUser.getName());
        Account account = user.getAccount();
        Double value = category.getActualValue().doubleValue();
        Double doubleOldValue = Double.parseDouble(oldValue) * 100d;
        Double result = 0.0;
        if (doubleOldValue < value) {
            result = value - doubleOldValue;
            Long categoryValue = result.longValue();
            accountService.setBalance(account.getId(), account.getBalance() - categoryValue);
        } else {
            result = doubleOldValue - value;
            Long categoryValue = result.longValue();
            accountService.setBalance(account.getId(), account.getBalance() + categoryValue);
        }
        categoryService.edit(convertedCategory);
        String htmlToReturn = "showDetails" + "/" + category.getCategoryName();
        return "redirect:" + htmlToReturn;
    }

}
