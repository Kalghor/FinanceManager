package pl.coderslab.FinanceManager.domain.convarter;

import pl.coderslab.FinanceManager.domain.dto.CategoryDto;
import pl.coderslab.FinanceManager.domain.model.Category;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class CategoryConverter {

    public Category convert(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        Double doubleCategoryValue = Double.parseDouble(categoryDto.getActualValue());
        doubleCategoryValue = doubleCategoryValue * 100d;
        category.setActualValue(doubleCategoryValue.longValue());
        category.setLocalDate(LocalDate.now());
        category.setScheduled(categoryDto.isScheduled());
        if (categoryDto.getSchedulingDate() != null && categoryDto.getSchedulingDate().length() > 0) {
            category.setSchedulingDate(getDateFromString(categoryDto.getSchedulingDate()));
        }
        if (categoryDto.getSchedulingStopDate() != null && categoryDto.getSchedulingStopDate().length() > 0) {
            category.setSchedulingStopDate(getDateFromString(categoryDto.getSchedulingStopDate()));
        }
        category.setMonthly(categoryDto.isMonthly());
        return category;
    }

    public Category convert(CategoryDto categoryDto, Category category) {
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        Double doubleCategoryValue = Double.parseDouble(categoryDto.getActualValue());
        doubleCategoryValue = doubleCategoryValue * 100d;
        category.setActualValue(doubleCategoryValue.longValue());
        category.setScheduled(categoryDto.isScheduled());
        if (categoryDto.getSchedulingDate() != null && categoryDto.getSchedulingDate().length() > 0) {
            category.setSchedulingDate(getDateFromString(categoryDto.getSchedulingDate()));
        }
        if (categoryDto.getSchedulingStopDate() != null && categoryDto.getSchedulingStopDate().length() > 0) {
            category.setSchedulingStopDate(getDateFromString(categoryDto.getSchedulingStopDate()));
        }
        category.setMonthly(categoryDto.isMonthly());
        return category;
    }

    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setDescription(category.getDescription());
        Double actualValue = category.getActualValue().doubleValue();
        actualValue = actualValue / 100;
        categoryDto.setActualValue(actualValue.toString());
        categoryDto.setLocalDate(category.getLocalDate());
        categoryDto.setScheduled(category.isScheduled());
        if (category.getSchedulingDate() != null) {
            categoryDto.setSchedulingDate(convertToString(category.getSchedulingDate()));
        }
        if (category.getSchedulingStopDate() != null) {
            categoryDto.setSchedulingStopDate(convertToString(category.getSchedulingStopDate()));
        }
        categoryDto.setMonthly(category.isMonthly());
        return categoryDto;
    }

    private LocalDate getDateFromString(String date) {
        String[] splitedDate = date.split("/");
//        (Integer.parseInt(splitedDate[2]), Integer.parseInt(splitedDate[0]), Integer.parseInt(splitedDate[1]));
        LocalDate parse = LocalDate.parse(splitedDate[2] + "-" + splitedDate[0] + "-" + splitedDate[1]);
        return LocalDate.parse(splitedDate[2] + "-" + splitedDate[0] + "-" + splitedDate[1]);
//        return LocalDate.ofInstant(Instant.parse(date.replaceAll("/","-")), ZoneId.of("Europe/Warsaw"));
    }

    private String convertToString(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

}
