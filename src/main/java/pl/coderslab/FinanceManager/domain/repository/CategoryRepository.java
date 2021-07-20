package pl.coderslab.FinanceManager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.FinanceManager.domain.model.Category;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.account.id = ?1")
    List<Category> findAllByUsername(Long userId);

    //TODO Nazwa metody nie do końca odpowiada temu co ona robi
    @Query("select DISTINCT c.categoryName from Category c where c.account.id = ?1")
    List<String> findDistinctByCategoryName(Long accountId);

    List<Category> findCategoriesByCategoryNameAndAccount_Id(String categoryName, Long accountId);

    void deleteCategoryById(Long id);

    Category findCategoryById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Category c where c.categoryName = ?1 and c.account.id = ?2")
    void deleteCategoriesByCategoryNameAndAccount_Id(String categoryName, Long accountId);
}
