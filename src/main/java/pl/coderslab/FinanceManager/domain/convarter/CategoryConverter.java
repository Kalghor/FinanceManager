package pl.coderslab.FinanceManager.domain.convarter;

import org.springframework.stereotype.Component;
import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Category;

import java.time.LocalDate;

public class CategoryConverter {

    public Category convert(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        category.setActualValue(categoryDto.getActualValue());
        category.setLocalDate(LocalDate.now());
        return category;
    }

    public Category convert(CategoryDto categoryDto, Category category){
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        category.setActualValue(categoryDto.getActualValue());
        return category;
    }

}
