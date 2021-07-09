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
        Double doubleCategoryValue = Double.parseDouble(categoryDto.getActualValue());
        doubleCategoryValue = doubleCategoryValue * 100d;
        category.setActualValue(doubleCategoryValue.longValue());
        category.setLocalDate(LocalDate.now());
        return category;
    }

    public Category convert(CategoryDto categoryDto, Category category){
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        Double doubleCategoryValue = Double.parseDouble(categoryDto.getActualValue());
        doubleCategoryValue = doubleCategoryValue * 100d;
        category.setActualValue(doubleCategoryValue.longValue());
        return category;
    }

    public CategoryDto convertToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setDescription(category.getDescription());
        Double actualValue = category.getActualValue().doubleValue();
        actualValue = actualValue / 100;
        categoryDto.setActualValue(actualValue.toString());
        categoryDto.setLocalDate(category.getLocalDate());
        return categoryDto;
    }

}
