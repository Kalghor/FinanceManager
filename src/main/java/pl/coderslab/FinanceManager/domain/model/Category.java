package pl.coderslab.FinanceManager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "actual_value")
    private Long actualValue;

    @Column(name = "date")
    private LocalDate localDate;

    @Column(name = "isScheduled")
    private boolean isScheduled;

    @Column(name = "scheduling_day")
    private LocalDate schedulingDate;

    @Column(name = "stop_scheduling_day")
    private LocalDate schedulingStopDate;

    @Column(name = "monthly_scheduling")
    private boolean monthly;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
