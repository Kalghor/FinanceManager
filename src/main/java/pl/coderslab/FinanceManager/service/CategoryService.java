package pl.coderslab.FinanceManager.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.FinanceManager.domain.model.Account;
import pl.coderslab.FinanceManager.domain.model.Category;
import pl.coderslab.FinanceManager.domain.repository.CategoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findCategories(Long userId) {
        return categoryRepository.findAllByUsername(userId);
    }

    public List<Category> findCategoryByName(String categoryName, Long accountId) {
        return categoryRepository.findCategoriesByCategoryNameAndAccount_Id(accountId, categoryName);
    }


    public List<String> findCategoryNames(Long accountId) {
        return categoryRepository.findDistinctByCategoryName(accountId);
    }

    public void addExpense(Category category) {
        categoryRepository.save(category);
    }

    public void deleteOneEntry(Long id) {
        categoryRepository.deleteCategoryById(id);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    public void edit(Category category) {
        categoryRepository.save(category);
    }

    public void deleteAllFromCategory(String categoryName, Long accountId) {
        categoryRepository.deleteCategoriesByCategoryNameAndAccount_Id(categoryName, accountId);
    }

    public List<Category> getScheduledExpenses(Long accountId){
        return categoryRepository.findCategoriesByIsScheduledAndAccount_Id(accountId);
    }

    public void deleteAllScheduled(Long accountId){
        categoryRepository.deleteAllScheduled(accountId);
    }

    public Long getLastCategoryIndex(Long accountId){
        return  categoryRepository.findLastCategoryIndex(accountId);
    }

    public Category prepareCategoryForDemo(Account account){
        Category category = new Category();
        category.setId(9999L);
        category.setActualValue(500L);
        category.setMonthly(true);
        category.setCategoryName("Demo scheduling");
        category.setAccount(account);
        category.setDescription("Demo test");
        category.setLocalDate(LocalDate.now());
        category.setScheduled(true);

        return category;
    }
}
