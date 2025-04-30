package org.rog.library.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "application_users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "middle_name")
    private String middleName;
    @Column (name = "birth_date")
    private LocalDate birthDate;
    @Column (name = "gender")
    private String gender;
    @OneToMany (mappedBy = "applicationUser", fetch = FetchType.LAZY)
    private List<Book> books;
}
