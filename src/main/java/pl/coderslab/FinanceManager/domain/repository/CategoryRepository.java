package pl.coderslab.FinanceManager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.FinanceManager.domain.model.Category;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.account.id = ?1 and c.isScheduled = 'false'")
    List<Category> findAllByUsername(Long userId);

    @Query("select DISTINCT c.categoryName from Category c where c.account.id = ?1 and c.isScheduled = 'false'")
    List<String> findDistinctByCategoryName(Long accountId);

    @Query("select DISTINCT c from Category c where c.account.id = ?1 and c.categoryName = ?2 and c.isScheduled = 'false'")
    List<Category> findCategoriesByCategoryNameAndAccount_Id(Long accountId, String categoryName);

    @Query("select DISTINCT c from Category c where c.account.id = ?1 and c.isScheduled = 'true'")
    List<Category> findCategoriesByIsScheduledAndAccount_Id(Long accountId);

    void deleteCategoryById(Long id);

    Category findCategoryById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Category c where c.categoryName = ?1 and c.account.id = ?2 and c.isScheduled = 'false'")
    void deleteCategoriesByCategoryNameAndAccount_Id(String categoryName, Long accountId);

    @Transactional
    @Modifying
    @Query("delete from Category c where c.categoryName = ?1 and c.account.id = ?2 and c.isScheduled = 'true'")
    void deleteScheduledCategoriesByCategoryNameAndAccount_Id(String categoryName, Long accountId);
}
