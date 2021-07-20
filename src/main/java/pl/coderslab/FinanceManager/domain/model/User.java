package pl.coderslab.FinanceManager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank @Email
    private String username;
    @Column(name = "first_name", unique = true)
    @NotBlank @Size(min = 3, max = 15)
    private String firstName;
    @Column(name = "last_name")
    @NotBlank @Size(min = 3, max = 15)
    private String lastName;
    @Column(nullable = false)
    //TODO Rzeczy niepotrzebne usuwamy, kodu się nie komentuje ;)
    //TODO Pattern można mieć na Dto związanym z rejestracją użytkownika, bo wtedy
    //     będzie dotyczyć danych wejściowych (czyli w plain text),
    //     a tak dotyczy hasła zaszyfrowanego ;)
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")
    private String password;
    @Column(nullable = false)
    private String role;
    @OneToOne(mappedBy = "owner")
    private Account account;


}
