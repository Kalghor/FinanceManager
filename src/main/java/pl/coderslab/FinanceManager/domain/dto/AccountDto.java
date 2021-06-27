package pl.coderslab.FinanceManager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AccountDto {
//    @Pattern(regexp = "^(?=.)([+-]?([0-9]*)(.([0-9]{1,2}))?)$")
    private String balance;
//    @Pattern(regexp = "^(?=.)([+-]?([0-9]*)(.([0-9]{1,2}))?)$")
    private String amountToAdd;
}
