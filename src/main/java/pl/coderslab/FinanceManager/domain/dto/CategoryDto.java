package pl.coderslab.FinanceManager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String description;
    private String actualValue;
    private LocalDate localDate;
}
