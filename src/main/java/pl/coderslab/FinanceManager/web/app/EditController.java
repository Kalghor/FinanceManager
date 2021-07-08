package pl.coderslab.FinanceManager.web.app;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.convarter.CategoryConverter;
import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.service.CategoryService;
import pl.coderslab.FinanceManager.service.UserManagerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/app")
public class EditController {

    private final CategoryService categoryService;
    private final UserManagerService userManagerService;

    public EditController(CategoryService categoryService, UserManagerService userManagerService) {
        this.categoryService = categoryService;
        this.userManagerService = userManagerService;
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
        CategoryConverter categoryConverter = new CategoryConverter();
        User user = userManagerService.findByUsername(currentUser.getName());
//        Category category1 = new Category(categoryDto.getId(), categoryDto.getCategoryName(), categoryDto.getDescription(), categoryDto.getActualValue(), categoryDto.getLocalDate(), user.getAccount());
        Category category = categoryService.findCategoryById(categoryDto.getId());
        Category convertedCategory = categoryConverter.convert(categoryDto, category);
//        category.setAccount();
        categoryService.edit(convertedCategory);
        return "redirect:dashboard";
    }

}
