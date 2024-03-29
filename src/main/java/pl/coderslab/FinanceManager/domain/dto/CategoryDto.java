package pl.coderslab.FinanceManager.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotBlank
//    @Pattern(regexp = "[a-zA-Z ]+")
    private String categoryName;

    @NotBlank
//    @Pattern(regexp = "[a-zA-Z ]+")
    private String description;

    @NotBlank
//    @Pattern(regexp = "(^[1-9]{1,1}[0-9]+\\.[0-9]{1,2})||(^[1-9]\\d+)||([1-9]+)||(0\\.[0-9]{1,2})")
    private String actualValue;

    private LocalDate localDate;

    private boolean isScheduled;

    private String schedulingDate;

    private String schedulingStopDate;

    private boolean monthly;
}
