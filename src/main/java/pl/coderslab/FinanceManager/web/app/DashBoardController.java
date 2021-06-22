package pl.coderslab.FinanceManager.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/app")
public class DashBoardController {

    private CategoryService categoryService;

    public DashBoardController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findCategories();
    }

    @GetMapping("/dashboard")
    public String showDashBoard(){
        return "dashboard";
    }

}
