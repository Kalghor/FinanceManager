package pl.coderslab.FinanceManager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long balance = 0L;

    @OneToOne
    @JoinColumn(name = "username")
    private User owner;

    @OneToMany(mappedBy = "account")
    private List<Category> categories;

}
