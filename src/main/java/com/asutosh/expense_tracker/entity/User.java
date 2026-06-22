package com.asutosh.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    @OneToMany(
            mappedBy = "user"
    )
    @JsonIgnore
    private List<Expense> expenses;

}