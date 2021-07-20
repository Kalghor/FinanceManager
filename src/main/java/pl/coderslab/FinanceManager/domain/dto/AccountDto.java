package pl.coderslab.FinanceManager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    //TODO Czy to jest coś w rodzaju kwoty? Nie lepiej wtedy użyć typu liczbowego + walidacja?
    //TODO W regex mamy "|" a nie "||" -> ()|()|()
    @Pattern(regexp = "(^[1-9]{1,1}[0-9]+\\.[0-9]{1,2})||(^[1-9]\\d+)||([1-9]+)||(0\\.[0-9]{1,2})")
    private String balance;

    @Pattern(regexp = "(^[1-9]{1,1}[0-9]+\\.[0-9]{1,2})||(^[1-9]\\d+)||([1-9]+)||(0\\.[0-9]{1,2})")
    private String amountToAdd;
}
