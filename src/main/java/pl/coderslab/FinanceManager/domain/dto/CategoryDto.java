package pl.coderslab.FinanceManager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String description;
    @NotBlank
    private String actualValue;
    private LocalDate localDate;
}
